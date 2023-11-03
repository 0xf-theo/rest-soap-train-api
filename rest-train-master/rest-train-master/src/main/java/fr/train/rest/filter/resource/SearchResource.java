package fr.train.rest.filter.resource;

import fr.train.rest.filter.model.ESeatCategory;
import fr.train.rest.filter.repository.TrainRepository;
import org.restlet.ext.gson.GsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class SearchResource extends ServerResource {

    private TrainRepository trainRepository;

    public SearchResource() {
        this.trainRepository = new TrainRepository();
    }

    @Get
    public Representation search() {
        String departure = getQueryValue("departure");
        String arrival = getQueryValue("arrival");
        String nb_tickets = getQueryValue("nb_tickets");
        String category = getQueryValue("category");
        String outbound_date = getQueryValue("outbound_date");
        String outbound_time = getQueryValue("outbound_time");
        String return_date = getQueryValue("return_date");
        String return_time = getQueryValue("return_time");

        var trains = trainRepository.all().stream()
                .filter(train -> train.getDeparture().equalsIgnoreCase(departure))
                .filter(train -> train.getArrival().equalsIgnoreCase(arrival))
                .filter(train -> nb_tickets == null || category == null || train.getSeats().stream().filter(seat -> seat.getCategory().equals(ESeatCategory.valueOf(category))).count() >= Integer.parseInt(nb_tickets))
                .filter(train -> {
                    var good = false;
                    if (outbound_date != null) {
                        good = train.getDepartureDateTime().toLocalDate().isEqual(LocalDate.parse(outbound_date));
                    }

                    if (return_date != null) {
                        good = train.getDepartureDateTime().toLocalDate().isEqual(LocalDate.parse(return_date));
                    }

                    return (good) || (outbound_date == null && return_date == null);
                })
                .filter(train -> {
                    if (outbound_time != null) {
                        return Math.abs(Duration.between(train.getDepartureDateTime().toLocalTime(), LocalTime.parse(outbound_time)).get(ChronoUnit.SECONDS) ) <= 3 * 3600;
                    }

                    if (return_time != null) {
                        return Math.abs(Duration.between(train.getDepartureDateTime().toLocalTime(), LocalTime.parse(return_time)).get(ChronoUnit.SECONDS)) <= 3 * 3600;
                    }
                    return true;
                })
                .toList();

        if (trains.isEmpty()) {
            return new StringRepresentation("no trains");
        }

        return new GsonRepresentation<>(trains);
    }
}
