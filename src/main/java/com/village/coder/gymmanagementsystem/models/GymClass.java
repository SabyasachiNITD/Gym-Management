package com.village.coder.gymmanagementsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class GymClass {
    private int id;
    private String className;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private Duration duration;
    private int capacity;
}
