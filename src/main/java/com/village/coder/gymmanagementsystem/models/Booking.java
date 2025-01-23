package com.village.coder.gymmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Booking {
    int classId;
    String memberName;
    LocalDate participationDate;
}
