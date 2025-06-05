package com.example.airqualityapp.Service;


import com.example.airqualityapp.Model.Reading;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class readingService {
    private Reading reading;

    public readingService(Reading reading) {
        this.reading = reading;
    }

//    public getClosestData(Date date){
//        return;
//    }



}
