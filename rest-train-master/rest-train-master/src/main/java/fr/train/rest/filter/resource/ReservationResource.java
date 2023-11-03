package fr.train.rest.filter.resource;

import fr.train.rest.filter.model.Passenger;
import fr.train.rest.filter.model.Reservation;
import fr.train.rest.filter.model.Seat;
import fr.train.rest.filter.repository.TrainRepository;
import io.ebean.Model;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ReservationResource extends ServerResource {

    private TrainRepository trainRepository;

    public ReservationResource() {
        this.trainRepository = new TrainRepository();
    }

    @Post
    public Representation add(Representation entity) {
        Form form = new Form(entity);

        var firstName = form.getFirstValue("firstName");
        var lastName = form.getFirstValue("lastName");
        var email = form.getFirstValue("email");
        var phoneNumber = form.getFirstValue("phoneNumber");
        var seats = form.getFirstValue("seats");
        try {
            var trains = Arrays.stream(form.getFirstValue("trains").split(","))
                    .map(t -> trainRepository.byId(UUID.fromString(t)).orElseThrow())
                    .toList();

            System.out.println(trains);

            var passenger = new Passenger();
            passenger.setId(UUID.randomUUID());
            passenger.setEmail(email);
            passenger.setFirstName(firstName);
            passenger.setLastName(lastName);
            passenger.setPhoneNumber(phoneNumber);
            passenger.save();

            var reservation = new Reservation();
            reservation.setId(UUID.randomUUID());
            reservation.setOutboundTrain(trains.get(0));
            reservation.setReturnTrain(trains.size() > 1 ? trains.get(1) : null);

            reservation.setPassenger(passenger);

            var seatss = new ArrayList<Seat>();

            Arrays.stream(seats.split(",")).forEach(sd -> reservation.getOutboundTrain().getSeats().stream()
                    .filter(s -> s.getName().equals(sd))
                    .findFirst()
                    .ifPresent(seat -> {
                        seat.setReservation(reservation);
                        reservation.setSeat(seat);
                        seatss.add(seat);
                        //seat.save();
                    }));

            if(reservation.getReturnTrain() != null){
                Arrays.stream(seats.split(",")).forEach(sd -> reservation.getReturnTrain().getSeats().stream()
                        .filter(s -> s.getName().equals(sd))
                        .findFirst()
                        .ifPresent(seat -> {
                            seat.setReservation(reservation);
                            seatss.add(seat);
                            //seat.save();
                        }));

            }

            reservation.save();
            seatss.forEach(Model::save);


            return new StringRepresentation("true");
        } catch (Exception e) {
            e.printStackTrace();
            return new StringRepresentation("false");
        }
    }
}
