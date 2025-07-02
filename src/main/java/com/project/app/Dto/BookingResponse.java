package com.project.app.Dto;

import org.springframework.stereotype.Component;



public class BookingResponse {
    private String username;
    private String trainName;
    private int seatsBooked;

    public BookingResponse(String username) {
        this.username = username;
    }

    public BookingResponse(String username, String trainName, int seatsBooked) {
        this.username = username;
        this.trainName = trainName;
        this.seatsBooked = seatsBooked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }
}

