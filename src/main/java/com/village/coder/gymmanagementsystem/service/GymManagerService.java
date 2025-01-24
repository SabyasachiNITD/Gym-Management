package com.village.coder.gymmanagementsystem.service;

import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GymManagerService implements IService{

    public Map<Integer,GymClass> gymClassList= new HashMap<>();
    public Map<String,List<Booking>> bookingList = new HashMap<>();
    public Map<LocalDate,List<Booking>> bookingDateList = new HashMap<>();
    @Override
    public GymClass createGymClass(GymClass gymClass) {
        GymClass newGymClass = new GymClass();
        try{
            if(gymClass.getEndDate().isBefore(gymClass.getStartDate())){
                throw new IllegalArgumentException("End date must be greater than start date");
            }
            if(gymClass.getCapacity()<1){
                throw new IllegalArgumentException("Capacity must be greater than 0");
            }
            int id = gymClass.getClassId();
            newGymClass.setClassId(id);
            newGymClass.setClassName(gymClass.getClassName());
            newGymClass.setDuration(gymClass.getDuration());
            newGymClass.setCapacity(gymClass.getCapacity());
            newGymClass.setStartDate(gymClass.getStartDate());
            newGymClass.setEndDate(gymClass.getEndDate());
            gymClassList.put(gymClass.getClassId(),newGymClass);
            return newGymClass;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Booking createBooking(Booking booking) {

        int classId = booking.getClassId();
        int classCapacity = gymClassList.get(classId).getCapacity();

        if(classCapacity<1){
            throw new IllegalArgumentException("This class is already fully booked");
        }
        Booking newBooking = new Booking();
        newBooking.setClassId(classId);
        gymClassList.get(classId).setCapacity(classCapacity-1);
        newBooking.setMemberName(booking.getMemberName());
        newBooking.setParticipationDate(booking.getParticipationDate());
        List<Booking> bookings = bookingList.get(booking.getMemberName());
        if(bookings==null){
            bookings = new ArrayList<>();
        }
        bookings.add(newBooking);
        List<Booking> bookingByDate = bookingDateList.get(booking.getParticipationDate());
        if(bookingByDate==null){
            bookingByDate = new ArrayList<>();
        }
        bookingByDate.add(newBooking);
        bookingList.put(booking.getMemberName(),bookings);
        bookingDateList.put(booking.getParticipationDate(),bookingByDate);
        return newBooking;
    }


    @Override
    public List<Booking> getAllBookingsByMember(String member) {
        List<Booking> bookings = bookingList.get(member);
        if(bookings==null){
            return null;
        }
        return bookings;
    }

    @Override
    public List<Booking> getAllBookingsByDate(LocalDate date) {
        List<Booking> bookings = bookingDateList.get(date);
        if(bookings==null){
            return null;
        }
        return bookings;
    }
}
