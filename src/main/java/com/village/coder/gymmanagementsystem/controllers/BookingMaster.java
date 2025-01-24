package com.village.coder.gymmanagementsystem.controllers;


import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import com.village.coder.gymmanagementsystem.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("{member}")
    public List<Booking> getAllBookingsByMember(@PathVariable("member") String member) {
        return service.getAllBookingsByMember(member);
    }
    @GetMapping("{date}")
    public List<Booking> getAllBookingsByMember(@PathVariable("member") LocalDate date) {
        return service.getAllBookingsByDate(date);
    }
}
