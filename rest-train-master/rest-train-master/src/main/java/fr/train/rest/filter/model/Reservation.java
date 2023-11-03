package fr.train.rest.filter.model;

import com.google.gson.annotations.JsonAdapter;
import fr.train.rest.filter.adapter.ExcludeAdapter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Reservation extends BaseModel {

    @JsonAdapter(ExcludeAdapter.class)
    @ManyToOne(optional = false)
    private Train outboundTrain;

    @JsonAdapter(ExcludeAdapter.class)
    @ManyToOne(optional = true)
    private Train returnTrain;

    @ManyToOne(optional = false)
    private Passenger passenger;

    @OneToOne(optional = false)
    private Seat seat;

    public Train getOutboundTrain() {
        return outboundTrain;
    }

    public Train getReturnTrain() {
        return returnTrain;
    }

    public Reservation setReturnTrain(Train returnTrain) {
        this.returnTrain = returnTrain;
        return this;
    }

    public Reservation setOutboundTrain(Train outboundTrain) {
        this.outboundTrain = outboundTrain;
        return this;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Reservation setPassenger(Passenger passenger) {
        this.passenger = passenger;
        return this;
    }

    public Seat getSeat() {
        return seat;
    }

    public Reservation setSeat(Seat seat) {
        this.seat = seat;
        return this;
    }
}
