package com.project.app.Dto;


public class UserBookingResponse {
    private Long bookingId;
    private String trainName;
    private int seatsBooked;
    private String origin;
    private String destination;

    public UserBookingResponse(Long bookingId, String trainName, int seatsBooked, String origin, String destination) {
        this.bookingId = bookingId;
        this.trainName = trainName;
        this.seatsBooked = seatsBooked;
        this.origin = origin;
        this.destination = destination;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}

