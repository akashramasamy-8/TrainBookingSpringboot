package com.project.app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id" , foreignKey = @ForeignKey(name = "fk_booking_user"))
    private Users user;

    @ManyToOne
    @JoinColumn(name="train_id" , foreignKey = @ForeignKey(name = "fk_booking_train"))
    private Trains train;

    @Column(nullable = false)
    private int seatsBooked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Trains getTrain() {
        return train;
    }

    public void setTrain(Trains train) {
        this.train = train;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }
}
