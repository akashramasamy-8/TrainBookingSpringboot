package com.project.app.service;

import com.project.app.Dto.BookingResponse;
import com.project.app.model.Bookings;
import com.project.app.model.Trains;
import com.project.app.repository.BookingRepository;
import com.project.app.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private TrainsRepository trainsRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public void addTrain(Trains train){
        trainsRepository.save(train);
    }

    public void updateTrainDetail(Long id,Trains train){
        Optional<Trains> trainn= trainsRepository.findById(id);
        if(trainn.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Train not found");
        }
        Trains t= trainn.get();
        if(train.getTrainNumber()!=0){
            t.setTrainNumber(train.getTrainNumber());
        }
        if(train.getName()!=null){
            t.setName(train.getName());
        }
        if(train.getDestination()!=null){
            t.setDestination(train.getDestination());
        }
        if(train.getOrigin()!=null){
            t.setOrigin(train.getOrigin());
        }
        if(train.getArrivalTime()!=null){
            t.setArrivalTime(train.getArrivalTime());
        }
        if(train.getDepartureTime()!=null){
            t.setDepartureTime(train.getDepartureTime());
        }
        if(train.getTotalSeats()!=0){
            t.setTotalSeats(train.getTotalSeats());
        }

        trainsRepository.save(t);
    }


    public List<BookingResponse> getBookings(Long id){
        List<Bookings> bookings=bookingRepository.findAllBytrain_id(id);
        return bookings.stream()
                .map(b -> new BookingResponse(
                       b.getUser().getUsername(),
                       b.getTrain().getName(),
                       b.getSeatsBooked()
                ))
                .collect(Collectors.toList());
    }
}
