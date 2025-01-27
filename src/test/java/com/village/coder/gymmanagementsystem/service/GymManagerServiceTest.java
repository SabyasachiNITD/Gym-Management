package com.village.coder.gymmanagementsystem.service;

import com.village.coder.gymmanagementsystem.exceptions.*;
import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import com.village.coder.gymmanagementsystem.models.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GymManagerServiceTest {

    @InjectMocks
    private GymManagerService gymManagerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Store.gymClassList.clear();
        Store.bookingList.clear();
        Store.bookingDateList.clear();
    }

    @Test
    public void testCreateGymClass_validClass_createsGymClass() {
        GymClass gymClass = new GymClass();
        gymClass.setClassId(1);
        gymClass.setClassName("Yoga");
        gymClass.setStartDate(LocalDate.of(2023, 1, 1));
        gymClass.setEndDate(LocalDate.of(2023, 12, 31));
        gymClass.setCapacity(20);
        gymClass.setDuration(60);
        gymClass.setStartTime(LocalTime.of(10, 0));

        GymClass createdGymClass = gymManagerService.createGymClass(gymClass);

        assertNotNull(createdGymClass);
        assertEquals(gymClass.getClassId(), createdGymClass.getClassId());
        assertEquals(gymClass.getClassName(), createdGymClass.getClassName());
        assertEquals(gymClass.getCapacity(), createdGymClass.getCapacity());
    }

    @Test
    public void testCreateGymClass_invalidEndDate_throwsEndDateIsLessThanStartDateException() {
        GymClass gymClass = new GymClass();
        gymClass.setClassId(1);
        gymClass.setClassName("Yoga");
        gymClass.setStartDate(LocalDate.of(2023, 1, 1));
        gymClass.setEndDate(LocalDate.of(2022, 12, 31));  // Invalid end date
        gymClass.setCapacity(20);
        gymClass.setDuration(60);
        gymClass.setStartTime(LocalTime.of(10, 0));

        assertThrows(RuntimeException.class, () -> {
            gymManagerService.createGymClass(gymClass);
        });
    }

    @Test
    public void testCreateBooking_validBooking_createsBooking() {
        GymClass gymClass = new GymClass();
        gymClass.setClassId(1);
        gymClass.setClassName("Yoga");
        gymClass.setStartDate(LocalDate.of(2023, 1, 1));
        gymClass.setEndDate(LocalDate.of(2023, 12, 31));
        gymClass.setCapacity(20);
        gymClass.setDuration(60);
        gymClass.setStartTime(LocalTime.of(10, 0));
        Store.gymClassList.put(1, gymClass);

        Booking booking = new Booking();
        booking.setClassId(1);
        booking.setMemberName("John Doe");
        booking.setParticipationDate(LocalDate.of(2023, 1, 5));

        Booking createdBooking = gymManagerService.createBooking(booking);

        assertNotNull(createdBooking);
        assertEquals(booking.getClassId(), createdBooking.getClassId());
        assertEquals(booking.getMemberName(), createdBooking.getMemberName());
        assertEquals(booking.getParticipationDate(), createdBooking.getParticipationDate());
    }

    @Test
    public void testCreateBooking_classIdNotFound_throwsClassIdNotFoundException() {
        Booking booking = new Booking();
        booking.setClassId(99);  // Non-existent class ID
        booking.setMemberName("John Doe");
        booking.setParticipationDate(LocalDate.of(2023, 1, 5));

        assertThrows(RuntimeException.class, () -> {
            gymManagerService.createBooking(booking);
        });
    }

    @Test
    public void testCreateBooking_capacityFull_throwsClassCapacityFullException() {
        GymClass gymClass = new GymClass();
        gymClass.setClassId(1);
        gymClass.setClassName("Yoga");
        gymClass.setStartDate(LocalDate.of(2023, 1, 1));
        gymClass.setEndDate(LocalDate.of(2023, 12, 31));
        gymClass.setCapacity(1);  // Only 1 spot available
        gymClass.setDuration(60);
        gymClass.setStartTime(LocalTime.of(10, 0));
        Store.gymClassList.put(1, gymClass);

        Booking booking1 = new Booking();
        booking1.setClassId(1);
        booking1.setMemberName("John Doe");
        booking1.setParticipationDate(LocalDate.of(2023, 1, 5));
        gymManagerService.createBooking(booking1);  // First booking

        Booking booking2 = new Booking();
        booking2.setClassId(1);
        booking2.setMemberName("Jane Doe");
        booking2.setParticipationDate(LocalDate.of(2023, 1, 5));


        assertThrows(RuntimeException.class, () -> {
            gymManagerService.createBooking(booking2);  // Second booking, should throw exception
        });
    }

    @Test
    public void testGetAllBookingsByMember_returnsBookings() {
        GymClass gymClass = new GymClass();
        gymClass.setClassId(1);
        gymClass.setClassName("Yoga");
        gymClass.setStartDate(LocalDate.of(2023, 1, 1));
        gymClass.setEndDate(LocalDate.of(2023, 12, 31));
        gymClass.setCapacity(20);
        gymClass.setDuration(60);
        gymClass.setStartTime(LocalTime.of(10, 0));
        Store.gymClassList.put(1, gymClass);

        Booking booking = new Booking();
        booking.setClassId(1);
        booking.setMemberName("John Doe");
        booking.setParticipationDate(LocalDate.of(2023, 1, 5));
        gymManagerService.createBooking(booking);

        List<Booking> bookings = gymManagerService.getAllBookingsByMember("John Doe");

        assertNotNull(bookings);
        assertEquals(1, bookings.size());
        assertEquals("John Doe", bookings.get(0).getMemberName());
    }

    @Test
    public void testGetAllBookingsByDate_returnsBookings() {
        GymClass gymClass = new GymClass();
        gymClass.setClassId(1);
        gymClass.setClassName("Yoga");
        gymClass.setStartDate(LocalDate.of(2023, 1, 1));
        gymClass.setEndDate(LocalDate.of(2023, 12, 31));
        gymClass.setCapacity(20);
        gymClass.setDuration(60);
        gymClass.setStartTime(LocalTime.of(10, 0));
        Store.gymClassList.put(1, gymClass);

        Booking booking = new Booking();
        booking.setClassId(1);
        booking.setMemberName("John Doe");
        booking.setParticipationDate(LocalDate.of(2023, 1, 5));
        gymManagerService.createBooking(booking);

        List<Booking> bookings = gymManagerService.getAllBookingsByDate(LocalDate.of(2023, 1, 5));

        assertNotNull(bookings);
        assertEquals(1, bookings.size());
        assertEquals("John Doe", bookings.get(0).getMemberName());
    }
}
