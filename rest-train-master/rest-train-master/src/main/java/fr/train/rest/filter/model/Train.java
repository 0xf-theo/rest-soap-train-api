package fr.train.rest.filter.model;


import com.google.gson.annotations.JsonAdapter;
import fr.train.rest.filter.adapter.ExcludeAdapter;
import fr.train.rest.filter.adapter.LocalDateTimeAdapter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Train extends BaseModel {

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String arrival;

    @JsonAdapter(LocalDateTimeAdapter.class)
    @Column(nullable = false)
    private LocalDateTime departureDateTime;

    @JsonAdapter(LocalDateTimeAdapter.class)
    @Column(nullable = false)
    private LocalDateTime arrivalDateTime;

    @JsonAdapter(ExcludeAdapter.class)
    @OneToMany(mappedBy = "outboundTrain")
    private List<Reservation> reservationsDeparture;

    @JsonAdapter(ExcludeAdapter.class)
    @OneToMany(mappedBy = "returnTrain")
    private List<Reservation> reservationsReturn;

    @Transient
    private List<Reservation> reservations;

    @OneToMany
    private List<Seat> seats;

    @JsonAdapter(ExcludeAdapter.class)
    @Column(nullable = false)
    private Double defaultPrice;

    public String getDeparture() {
        return departure;
    }

    public Train setDeparture(String departure) {
        this.departure = departure;
        return this;
    }

    public String getArrival() {
        return arrival;
    }

    public Train setArrival(String arrival) {
        this.arrival = arrival;
        return this;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public Train setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
        return this;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public Train setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
        return this;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Train setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Train setSeats(List<Seat> seats) {
        this.seats = seats;
        return this;
    }

    public Double getDefaultPrice() {
        return defaultPrice;
    }

    public Train setDefaultPrice(Double defaultPrice) {
        this.defaultPrice = defaultPrice;
        return this;
    }

    public List<Reservation> getReservationsDeparture() {
        return reservationsDeparture;
    }

    public List<Reservation> getReservationsReturn() {
        return reservationsReturn;
    }
}
