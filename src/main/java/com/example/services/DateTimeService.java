package com.example.services;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

@Component
public class DateTimeService {

    public Timestamp getCurrentTimestamp(){
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }
}
