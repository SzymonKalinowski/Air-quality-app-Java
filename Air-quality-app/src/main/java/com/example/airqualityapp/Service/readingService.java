package com.example.airqualityapp.Service;


import com.example.airqualityapp.Model.Reading;
import org.springframework.stereotype.Service;

@Service
public class readingService {
    private Reading reading;

    public readingService(Reading reading) {
        this.reading = reading;
    }

    public Reading getClosestData(String date){
        return reading;
    }



}
