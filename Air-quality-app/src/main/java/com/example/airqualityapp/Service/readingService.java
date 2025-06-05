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

        ReadingClosestTimestamp.Hourly newHourly = new ReadingClosestTimestamp.Hourly(
                time.get(index),
                pressure_msl.get(index),
                relative_humidity_2m.get(index)
        );

        ReadingClosestTimestamp newReadingClosestTimestamp = new ReadingClosestTimestamp();
        newReadingClosestTimestamp.setTimestamp(reading.getTimestamp());


        Reading.Weather originalWeather = reading.getWeather();
        ReadingClosestTimestamp.Weather newWeather = new ReadingClosestTimestamp.Weather();

        newWeather.setLatitude(originalWeather.getLatitude());
        newWeather.setLongitude(originalWeather.getLongitude());
        newWeather.setGenerationtime_ms(originalWeather.getGenerationtime_ms());
        newWeather.setUtc_offset_seconds(originalWeather.getUtc_offset_seconds());
        newWeather.setTimezone(originalWeather.getTimezone());
        newWeather.setTimezone_abbreviation(originalWeather.getTimezone_abbreviation());
        newWeather.setElevation(originalWeather.getElevation());

        ReadingClosestTimestamp.CurrentWeatherUnits newCurrentWeatherUnits = new ReadingClosestTimestamp.CurrentWeatherUnits(
                originalWeather.getCurrent_weather_units().getTime(),
                originalWeather.getCurrent_weather_units().getInterval(),
                originalWeather.getCurrent_weather_units().getTemperature(),
                originalWeather.getCurrent_weather_units().getWindspeed(),
                originalWeather.getCurrent_weather_units().getWinddirection(),
                originalWeather.getCurrent_weather_units().getIs_day(),
                originalWeather.getCurrent_weather_units().getWeathercode()
        );
        newWeather.setCurrent_weather_units(newCurrentWeatherUnits);


        ReadingClosestTimestamp.CurrentWeather newCurrentWeather = new ReadingClosestTimestamp.CurrentWeather(
                originalWeather.getCurrent_weather().getTime(),
                originalWeather.getCurrent_weather().getInterval(),
                originalWeather.getCurrent_weather().getTemperature(),
                originalWeather.getCurrent_weather().getWindspeed(),
                originalWeather.getCurrent_weather().getWinddirection(),
                originalWeather.getCurrent_weather().getIs_day(),
                originalWeather.getCurrent_weather().getWeathercode()
        );
        newWeather.setCurrent_weather(newCurrentWeather);

        ReadingClosestTimestamp.HourlyUnits newHourlyUnits = new ReadingClosestTimestamp.HourlyUnits(
                originalWeather.getHourly_units().getTime(),
                originalWeather.getHourly_units().getPressure_msl(),
                originalWeather.getHourly_units().getRelative_humidity_2m()
        );
        newWeather.setHourly_units(newHourlyUnits);

        newWeather.setHourly(newHourly);
        newReadingClosestTimestamp.setWeather(newWeather);

        Reading.AirQuality originalAirQuality = reading.getAirQuality();
        ReadingClosestTimestamp.AirQuality newAirQuality = new ReadingClosestTimestamp.AirQuality(
                originalAirQuality.getPm10(),
                originalAirQuality.getPm2_5(),
                originalAirQuality.getCarbonMonoxide(),
                originalAirQuality.getNitrogenDioxide(),
                originalAirQuality.getSulphurDioxide(),
                originalAirQuality.getOzone()
        );
        newReadingClosestTimestamp.setAirQuality(newAirQuality);

        return newReadingClosestTimestamp;
    }

    public ReadingClosestTimestamp getClosestToGivenData(String date) {
        String timestamp = reading.getTimestamp();
        List<String> time = reading.getWeather().getHourly().getTime();
        List<Double> pressure_msl = reading.getWeather().getHourly().getPressure_msl();
        List<Integer> relative_humidity_2m = reading.getWeather().getHourly().getRelative_humidity_2m();

        Integer index = -1;
        if (time.contains(timestamp)) {
            index = time.indexOf(timestamp);
        }

        ReadingClosestTimestamp.Hourly newHourly = new ReadingClosestTimestamp.Hourly(
                time.get(index),
                pressure_msl.get(index),
                relative_humidity_2m.get(index)
        );

        ReadingClosestTimestamp newReadingClosestTimestamp = new ReadingClosestTimestamp();
        newReadingClosestTimestamp.setTimestamp(reading.getTimestamp());


        Reading.Weather originalWeather = reading.getWeather();
        ReadingClosestTimestamp.Weather newWeather = new ReadingClosestTimestamp.Weather();

        newWeather.setLatitude(originalWeather.getLatitude());
        newWeather.setLongitude(originalWeather.getLongitude());
        newWeather.setGenerationtime_ms(originalWeather.getGenerationtime_ms());
        newWeather.setUtc_offset_seconds(originalWeather.getUtc_offset_seconds());
        newWeather.setTimezone(originalWeather.getTimezone());
        newWeather.setTimezone_abbreviation(originalWeather.getTimezone_abbreviation());
        newWeather.setElevation(originalWeather.getElevation());

        ReadingClosestTimestamp.CurrentWeatherUnits newCurrentWeatherUnits = new ReadingClosestTimestamp.CurrentWeatherUnits(
                originalWeather.getCurrent_weather_units().getTime(),
                originalWeather.getCurrent_weather_units().getInterval(),
                originalWeather.getCurrent_weather_units().getTemperature(),
                originalWeather.getCurrent_weather_units().getWindspeed(),
                originalWeather.getCurrent_weather_units().getWinddirection(),
                originalWeather.getCurrent_weather_units().getIs_day(),
                originalWeather.getCurrent_weather_units().getWeathercode()
        );
        newWeather.setCurrent_weather_units(newCurrentWeatherUnits);


        ReadingClosestTimestamp.CurrentWeather newCurrentWeather = new ReadingClosestTimestamp.CurrentWeather(
                originalWeather.getCurrent_weather().getTime(),
                originalWeather.getCurrent_weather().getInterval(),
                originalWeather.getCurrent_weather().getTemperature(),
                originalWeather.getCurrent_weather().getWindspeed(),
                originalWeather.getCurrent_weather().getWinddirection(),
                originalWeather.getCurrent_weather().getIs_day(),
                originalWeather.getCurrent_weather().getWeathercode()
        );
        newWeather.setCurrent_weather(newCurrentWeather);

        ReadingClosestTimestamp.HourlyUnits newHourlyUnits = new ReadingClosestTimestamp.HourlyUnits(
                originalWeather.getHourly_units().getTime(),
                originalWeather.getHourly_units().getPressure_msl(),
                originalWeather.getHourly_units().getRelative_humidity_2m()
        );
        newWeather.setHourly_units(newHourlyUnits);

        newWeather.setHourly(newHourly);
        newReadingClosestTimestamp.setWeather(newWeather);

        Reading.AirQuality originalAirQuality = reading.getAirQuality();
        ReadingClosestTimestamp.AirQuality newAirQuality = new ReadingClosestTimestamp.AirQuality(
                originalAirQuality.getPm10(),
                originalAirQuality.getPm2_5(),
                originalAirQuality.getCarbonMonoxide(),
                originalAirQuality.getNitrogenDioxide(),
                originalAirQuality.getSulphurDioxide(),
                originalAirQuality.getOzone()
        );
        newReadingClosestTimestamp.setAirQuality(newAirQuality);

        return newReadingClosestTimestamp;
    }
}
