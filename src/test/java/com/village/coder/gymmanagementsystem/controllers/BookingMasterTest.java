package com.village.coder.gymmanagementsystem.controllers;

import com.village.coder.gymmanagementsystem.dtos.BookingDto;
import com.village.coder.gymmanagementsystem.dtos.GymClassDto;
import com.village.coder.gymmanagementsystem.models.Booking;
import com.village.coder.gymmanagementsystem.models.GymClass;
import com.village.coder.gymmanagementsystem.service.IService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookingMasterTest {

    @Mock
    private IService service;

    @InjectMocks
    private BookingMaster bookingMaster;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookingMaster).build();
    }

    @Test
    public void testCreateGymClass() throws Exception {
        GymClassDto gymClassDto = new GymClassDto();
        gymClassDto.setClassId(1);
        gymClassDto.setClassName("Yoga");
        gymClassDto.setStartDate("01-Jan-2023");
        gymClassDto.setEndDate("31-Jan-2023");
        gymClassDto.setCapacity(20);
        gymClassDto.setDuration(60);
        gymClassDto.setStartTime("10:00");

        GymClass gymClass = new GymClass();
        gymClass.setClassId(1);
        gymClass.setClassName("Yoga");
        gymClass.setStartDate(LocalDate.of(2023, 1, 1));
        gymClass.setEndDate(LocalDate.of(2023, 1, 31));
        gymClass.setCapacity(20);
        gymClass.setDuration(60);
        gymClass.setStartTime(LocalTime.of(10, 0));

        when(service.createGymClass(any(GymClass.class))).thenReturn(gymClass);

        mockMvc.perform(post("/bookings/gymclass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"classId\":1, \"className\":\"Yoga\", \"startDate\":\"01-Jan-2023\", \"endDate\":\"31-Jan-2023\", \"capacity\":20, \"duration\":60, \"startTime\":\"10:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.classId").value(1))
                .andExpect(jsonPath("$.className").value("Yoga"))
                .andExpect(jsonPath("$.capacity").value(20));
    }

    @Test
    public void testCreateBooking() throws Exception {

        BookingDto bookingDto = new BookingDto();
        bookingDto.setClassId(1);
        bookingDto.setMemberName("John Doe");
        bookingDto.setParticipationDate("01-Jan-2023");

        Booking booking = new Booking();
        booking.setClassId(1);
        booking.setMemberName("John Doe");
        booking.setParticipationDate(LocalDate.of(2023, 1, 1));

        when(service.createBooking(any(Booking.class))).thenReturn(booking);


        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"classId\":1, \"memberName\":\"John Doe\", \"participationDate\":\"01-Jan-2023\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberName").value("John Doe"))
                .andExpect(jsonPath("$.classId").value(1));
    }

    @Test
    public void testGetAllBookingsByMember() throws Exception {

        Booking booking = new Booking();
        booking.setClassId(1);
        booking.setMemberName("John Doe");
        booking.setParticipationDate(LocalDate.of(2023, 1, 1));

        when(service.getAllBookingsByMember("John Doe")).thenReturn(Collections.singletonList(booking));

        mockMvc.perform(get("/bookings/John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[0].classId").value(1));
    }

    @Test
    public void testGetAllBookingsByDate() throws Exception {

        Booking booking = new Booking();
        booking.setClassId(1);
        booking.setMemberName("John Doe");
        booking.setParticipationDate(LocalDate.of(2023, 1, 1));

        when(service.getAllBookingsByDate(LocalDate.of(2023, 1, 1))).thenReturn(Collections.singletonList(booking));

        mockMvc.perform(get("/bookings?date=01-Jan-2023"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[0].classId").value(1));
    }
}
