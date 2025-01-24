package com.village.coder.gymmanagementsystem.service;

import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;

import java.time.LocalDate;
import java.util.List;

public interface IService {
    GymClass createGymClass(GymClass gymClass);
    Booking createBooking(Booking booking);
    List<Booking> getAllBookingsByDate(LocalDate date);
    List<Booking> getAllBookingsByMember(String member);
}
