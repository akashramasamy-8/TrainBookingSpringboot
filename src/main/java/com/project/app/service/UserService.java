package com.project.app.service;

import com.project.app.Dto.UserBookingResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.app.model.Bookings;
import com.project.app.model.Trains;
import com.project.app.model.Users;
import com.project.app.repository.BookingRepository;
import com.project.app.repository.TrainsRepository;
import com.project.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Encoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private TrainsRepository trainsRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Trains> getAllTrains() {
        return trainsRepository.findAll();
    }

    public void bookTicket(Bookings booking) {
        bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<UserBookingResponse> getBookingsByUser(Long userId) {
        List<Bookings> bookings = bookingRepository.findByUserId(userId);

        return bookings.stream()
                .map(b -> new UserBookingResponse(
                        b.getId(),
                        b.getTrain().getName(),
                        b.getSeatsBooked(),
                        b.getTrain().getOrigin(),
                        b.getTrain().getDestination()
                ))
                .collect(Collectors.toList());
    }

    public void updateProfile(Users user) {
        passwordEncoder.encode(user.getPassword());
        usersRepository.save(user);
    }

}
