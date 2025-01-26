package com.village.coder.gymmanagementsystem.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    public static Map<Integer,GymClass> gymClassList= new HashMap<>();
    public static Map<String, List<Booking>> bookingList = new HashMap<>();
    public static Map<LocalDate,List<Booking>> bookingDateList = new HashMap<>();
}
