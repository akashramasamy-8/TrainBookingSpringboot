package com.project.app.controller;

import com.project.app.Dto.BookingResponse;
import com.project.app.model.Bookings;
import com.project.app.model.Trains;
import com.project.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String getMessage(){
        return "HI";
    }
    @PostMapping("/train")
    public ResponseEntity<String> addTrain(@RequestBody Trains train){
        adminService.addTrain(train);
        return new ResponseEntity<>("Train added successfully", HttpStatus.OK);
    }

    @PutMapping("/train/{id}")
    public ResponseEntity<String> updateTrain(@PathVariable Long id, @RequestBody Trains train){
        try {
            adminService.updateTrainDetail(id, train);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>("Train not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Train details updated succesfully", HttpStatus.OK);
    }

    @GetMapping("bookings/train/{id}")
    public ResponseEntity<List<BookingResponse>> getBookings(@PathVariable Long id){
        List<BookingResponse> list=adminService.getBookings(id);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}
