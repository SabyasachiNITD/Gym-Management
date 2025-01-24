package com.village.coder.gymmanagementsystem.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class GymClass {
    private int classId;
    private String className;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private int duration;
    private int capacity;
}
