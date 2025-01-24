package com.village.coder.gymmanagementsystem.service;

import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymManagerService implements IService{

    public List<GymClass> gymClassList= new ArrayList<GymClass>();
    public List<Booking> bookingList = new ArrayList<>();
    @Override
    public GymClass createGymClass(GymClass gymClass) {
        GymClass newGymClass = new GymClass();
        int id = gymClassList.size();
        newGymClass.setClassId(1);
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
