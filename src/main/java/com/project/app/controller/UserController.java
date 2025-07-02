package com.project.app.controller;

import com.project.app.Dto.UserBookingResponse;
import com.project.app.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import com.project.app.model.Bookings;
import com.project.app.model.Trains;
import com.project.app.model.Users;
import com.project.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/trains")
    public ResponseEntity<List<Trains>> viewTrains() {
        return ResponseEntity.ok(userService.getAllTrains());
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> bookTicket(@RequestBody Bookings booking) {
        userService.bookTicket(booking);
        return ResponseEntity.ok("Booking successful");
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        userService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled");
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<UserBookingResponse>> getUserBookings(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getBookingsByUser(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody Users updatedUser) {
        userService.updateProfile(updatedUser);
        return ResponseEntity.ok("Profile updated successfully");
    }



}
