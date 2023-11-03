package fr.train.rest.filter.model;

import com.google.gson.annotations.JsonAdapter;
import fr.train.rest.filter.adapter.ExcludeAdapter;

import javax.persistence.*;

@Table
@Entity
public class Seat extends BaseModel {

    @Column(nullable = false)
    private String name;

    @JsonAdapter(ExcludeAdapter.class)
    @ManyToOne(optional = false, targetEntity = Train.class)
    private Train train;

    @Column(nullable = false)
    private ESeatCategory category;

    @JsonAdapter(ExcludeAdapter.class)
    @OneToOne
    private Reservation reservation;

    @Transient
    private Double price;



    public String getName() {
        return name;
    }

    public Seat setName(String name) {
        this.name = name;
        return this;
    }

    public Train getTrain() {
        return train;
    }

    public Seat setTrain(Train train) {
        this.train = train;
        return this;
    }

    public ESeatCategory getCategory() {
        return category;
    }

    public Seat setCategory(ESeatCategory category) {
        this.category = category;
        return this;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Seat setReservation(Reservation reservation) {
        this.reservation = reservation;
        return this;
    }

    public Seat setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getPrice() {
        return price;
    }
}
