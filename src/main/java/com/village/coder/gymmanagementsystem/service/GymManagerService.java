package com.village.coder.gymmanagementsystem.service;

import com.village.coder.gymmanagementsystem.exceptions.*;
import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import com.village.coder.gymmanagementsystem.models.Store;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GymManagerService implements IService{
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
            Store.gymClassList.put(gymClass.getClassId(),newGymClass);
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
            if(!Store.gymClassList.containsKey(classId)){
                throw new ClassIdNotFoundException("Class Id Not Found");
            }
            LocalDate startDate = Store.gymClassList.get(classId).getStartDate();
            LocalDate endDate = Store.gymClassList.get(classId).getEndDate();
            if(startDate.isAfter(participationDate) || endDate.isBefore(participationDate)){
                throw new ParticipationDateNotValidException("Participation Date is not valid");
            }
            int classCapacity = Store.gymClassList.get(classId).getCapacity();
            if(Store.bookingDateList.containsKey(participationDate)){
                int participantsTillNow = (int) Store.bookingDateList.get(participationDate).stream()
                        .filter(e -> e.getClassId() == classId).count();
                System.out.println("classCapacity: " + classCapacity);
                System.out.println("participantsTillNow: " + participantsTillNow);
                if(participantsTillNow == classCapacity){
                    throw new ClassCapacityFullException("class capacity full for the day");
                }
            }
            Booking newBooking = new Booking();
            newBooking.setClassId(classId);
            newBooking.setMemberName(booking.getMemberName());
            newBooking.setParticipationDate(booking.getParticipationDate());
            List<Booking> bookings = Store.bookingList.get(booking.getMemberName());
            if(bookings==null){
                bookings = new ArrayList<>();
            }
            bookings.add(newBooking);
            List<Booking> bookingByDate = Store.bookingDateList.get(booking.getParticipationDate());
            if(bookingByDate==null){
                bookingByDate = new ArrayList<>();
            }
            bookingByDate.add(newBooking);
            Store.bookingList.put(booking.getMemberName(),bookings);
            Store.bookingDateList.put(booking.getParticipationDate(),bookingByDate);
            return newBooking;
        }
        catch (ClassIdNotFoundException | ClassCapacityFullException | ParticipationDateNotValidException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Booking> getAllBookingsByMember(String member) {
        System.out.println("Member: " + member);
        System.out.println("getAllBookingsByMember: " + Store.bookingList.get(member));
        return Store.bookingList.get(member);
    }

    @Override
    public List<Booking> getAllBookingsByDate(LocalDate date) {
        return Store.bookingDateList.get(date);
    }
}