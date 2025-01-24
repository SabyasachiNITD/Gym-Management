package com.village.coder.gymmanagementsystem.dtos;

import java.time.LocalDate;

public class BookingDto {
    int classId;
    String memberName;
    String participationDate;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(String participationDate) {
        this.participationDate = participationDate;
    }
}
