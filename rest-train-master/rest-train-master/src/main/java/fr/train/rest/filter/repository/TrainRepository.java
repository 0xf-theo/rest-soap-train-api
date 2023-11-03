package fr.train.rest.filter.repository;

import fr.train.rest.filter.model.Reservation;
import fr.train.rest.filter.model.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//gérer les opérations de recherche de trains par identifiant et de récupération de tous les trains en
//ajustant les prix des sièges et en collectant les réservations associées aux trains.
public class TrainRepository extends BeanRepository<Train> {

    // permet de spécifier que TrainRepository a pour objectif de gérer les objets Train
    public TrainRepository() {
        super(Train.class);
    }

    public List<Train> all() {
        return find()
                .findList()
                .stream()
                .peek(train -> {
                    train.getSeats()
                            .forEach(s -> s.setPrice(train.getDefaultPrice() * s.getCategory().getPriceFactor()));
                    train.setSeats(train.getSeats().stream().filter(t -> t.getReservation() == null).toList());
                })
                .peek(train -> {
                    var reservations = new ArrayList<Reservation>();
                    reservations.addAll(train.getReservationsDeparture());
                    reservations.addAll(train.getReservationsReturn());
                    train.setReservations(reservations);
                })
                .toList();
    }

    public Optional<Train> byId(UUID id) {
        return find().where()
                .eq("id", id)
                .findOneOrEmpty()
                .map(train -> {
                    var reservations = new ArrayList<Reservation>();
                    reservations.addAll(train.getReservationsDeparture());
                    reservations.addAll(train.getReservationsReturn());
                    train.setReservations(reservations);
                    train.getSeats()
                            .forEach(s -> s.setPrice(train.getDefaultPrice() * s.getCategory().getPriceFactor()));
                    train.setSeats(train.getSeats().stream().filter(t -> t.getReservation() == null).toList());
                    return train;
                });
    }
}
