package com.example.airqualityapp.Service;


import com.example.airqualityapp.Model.Reading;
import com.example.airqualityapp.Model.ReadingClosestTimestamp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class readingService {
    private Reading reading;

    public readingService(Reading reading) {
        this.reading = reading;
    }

    public ReadingClosestTimestamp getClosestToTimestamp() {
        String timestamp = reading.getTimestamp();
        List<String> time = reading.getWeather().getHourly().getTime();
        List<Double> pressure_msl = reading.getWeather().getHourly().getPressure_msl();
        List<Integer> relative_humidity_2m = reading.getWeather().getHourly().getRelative_humidity_2m();

        Integer index = -1;
        if (time.contains(timestamp)) {
            index = time.indexOf(timestamp);
        }

        String foundTime = time.get(index);
        Double correspondingPressure = pressure_msl.get(index);
        Integer correspondingHumidity = relative_humidity_2m.get(index);

        ReadingClosestTimestamp.Hourly HourlyData = new ReadingClosestTimestamp.Hourly();
        HourlyData.setTime(foundTime);
        HourlyData.setPressure_msl(correspondingPressure);
        HourlyData.setRelative_humidity_2m(correspondingHumidity);




    }



}
