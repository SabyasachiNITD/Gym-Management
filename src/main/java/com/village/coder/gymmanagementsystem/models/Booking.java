package com.village.coder.gymmanagementsystem.models;

import java.time.LocalDate;


public class Booking {
    private int classId;
    private String memberName;
    private LocalDate participationDate;

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
