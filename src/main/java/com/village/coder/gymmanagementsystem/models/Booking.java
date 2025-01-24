package com.village.coder.gymmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


public class Booking {
    int classId;
    String memberName;
    LocalDate participationDate;

    public int getClassId() {
        return classId;
    }

    public String getMemberName() {
        return memberName;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setParticipationDate(LocalDate participationDate) {
        this.participationDate = participationDate;
    }
}
