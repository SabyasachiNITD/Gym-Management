package com.village.coder.gymmanagementsystem.service;

import com.village.coder.gymmanagementsystem.exceptions.CapacityLessThenOneException;
import com.village.coder.gymmanagementsystem.exceptions.ClassCapacityFullException;
import com.village.coder.gymmanagementsystem.exceptions.ClassIdNotFoundException;
import com.village.coder.gymmanagementsystem.exceptions.EndDateIsLessThanStartDateException;
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
                throw new EndDateIsLessThanStartDateException("End date must be greater than start date");
            }
            if(gymClass.getCapacity()<1){
                throw new CapacityLessThenOneException("Capacity must be greater than 0");
            }
            int id = gymClass.getClassId();
            newGymClass.setClassId(id);
            newGymClass.setClassName(gymClass.getClassName());
            newGymClass.setDuration(gymClass.getDuration());
            newGymClass.setCapacity(gymClass.getCapacity());
            newGymClass.setStartDate(gymClass.getStartDate());
            newGymClass.setEndDate(gymClass.getEndDate());
            newGymClass.setStartTime(gymClass.getStartTime());
            gymClassList.put(gymClass.getClassId(),newGymClass);
            return newGymClass;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Booking createBooking(Booking booking) {

        int classId = booking.getClassId();
        LocalDate participationDate = booking.getParticipationDate();
        try{
            if(!gymClassList.containsKey(classId)){
                throw new ClassIdNotFoundException("Class Id Not Found");
            }
            int classCapacity = gymClassList.get(classId).getCapacity();
            if(bookingDateList.containsKey(participationDate)){
                int participantsTillNow = (int) bookingDateList.get(participationDate).stream()
                        .filter(e -> e.getClassId() == classId).count();
                if(participantsTillNow == classCapacity){
                    throw new ClassCapacityFullException("class capacity full for the day");
                }
            }
            Booking newBooking = new Booking();
            newBooking.setClassId(classId);
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
        catch (ClassIdNotFoundException | ClassCapacityFullException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Booking> getAllBookingsByMember(String member) {
        return bookingList.get(member);
    }

    @Override
    public List<Booking> getAllBookingsByDate(LocalDate date) {
        return bookingDateList.get(date);
    }
}