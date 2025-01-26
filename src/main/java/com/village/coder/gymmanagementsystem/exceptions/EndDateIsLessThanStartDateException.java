package com.village.coder.gymmanagementsystem.exceptions;

public class EndDateIsLessThanStartDateException extends Exception{
    public EndDateIsLessThanStartDateException(String msg){
        super(msg);
    }
}
