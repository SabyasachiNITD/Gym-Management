package com.village.coder.gymmanagementsystem.controllers;


import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import com.village.coder.gymmanagementsystem.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingMaster {
    @Autowired
    IService service;


    @PostMapping("/gymclass")
    public GymClass createClass(@RequestBody GymClass gymClass){
        return service.createGymClass(gymClass);
    }
    @PostMapping("")
    public Booking createBooking(@RequestBody Booking booking){
        return service.createBooking(booking);
    }

    @GetMapping("")
    public List<Booking> getAllBookings() {
        return service.getBookings();
    }
}
