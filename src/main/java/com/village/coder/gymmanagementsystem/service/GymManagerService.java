package com.village.coder.gymmanagementsystem.service;

import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymManagerService implements IService{


    @Override
    public GymClass createGymClass(GymClass gymClass) {
        return null;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return null;
    }

    @Override
    public List<Booking> getBookings() {
        return List.of();
    }
}
