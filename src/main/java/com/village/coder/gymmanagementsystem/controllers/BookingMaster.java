package com.village.coder.gymmanagementsystem.controllers;


import com.village.coder.gymmanagementsystem.dtos.BookingDto;
import com.village.coder.gymmanagementsystem.dtos.GymClassDto;
import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import com.village.coder.gymmanagementsystem.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.apache.tomcat.jni.SSL.getTime;

@RestController
@RequestMapping("/bookings")
public class BookingMaster {
    @Autowired
    IService service;


    @PostMapping("/gymclass")
    public GymClass createClass(@RequestBody GymClassDto gymClassDto){
        GymClass gymClass = getClassFromDto(gymClassDto);
        return service.createGymClass(gymClass);
    }

    private GymClass getClassFromDto(GymClassDto gymClassDto) {
        GymClass gymClass = new GymClass();
        gymClass.setClassId(gymClassDto.getClassId());
        gymClass.setClassName(gymClassDto.getClassName());
        gymClass.setStartDate(getDate(gymClassDto.getStartDate()));
        gymClass.setEndDate(getDate(gymClassDto.getEndDate()));
        gymClass.setCapacity(gymClassDto.getCapacity());
        gymClass.setDuration(gymClassDto.getDuration());
        gymClass.setStartTime(this.getTime(gymClassDto.getStartTime()));
        return gymClass;
    }

    private LocalDate getDate(String startDate) {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                                .parseCaseInsensitive()
                                .appendPattern("dd-MMM-yyyy")
                                .toFormatter(Locale.ENGLISH);
        return LocalDate.parse(startDate,dtf);
    }
    private LocalTime getTime(String startTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime lt = LocalTime.parse(startTime,dtf);
        return lt;
    }

    @PostMapping("")
    public Booking createBooking(@RequestBody BookingDto bookingDto){
        Booking booking = getBookingFromDto(bookingDto);
        return service.createBooking(booking);
    }

    private Booking getBookingFromDto(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setClassId(bookingDto.getClassId());
        booking.setParticipationDate(getDate(bookingDto.getParticipationDate()));
        booking.setMemberName(bookingDto.getMemberName());
        return booking;
    }

    @GetMapping("{member}")
    public List<Booking> getAllBookingsByMember(@PathVariable("member") String member) {
        return service.getAllBookingsByMember(member);
    }
    @GetMapping("")
    public List<Booking> getAllBookingsByDate(@RequestParam String date) {
        LocalDate dt = getDate(date);
        return service.getAllBookingsByDate(dt);
    }
}
