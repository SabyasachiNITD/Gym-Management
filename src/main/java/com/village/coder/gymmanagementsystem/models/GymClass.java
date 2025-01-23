package com.village.coder.gymmanagementsystem.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class GymClass {
    private int id;
    private String className;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private int duration;
    private int capacity;
}
