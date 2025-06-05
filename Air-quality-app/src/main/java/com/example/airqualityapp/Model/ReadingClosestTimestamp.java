package com.example.airqualityapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ReadingClosestTimestamp {

    @NotBlank
    private String timestamp;

    @NotNull
    private Weather weather;

    @NotNull
    private AirQuality airQuality;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather {
        @DecimalMin("-90.0") @DecimalMax("90.0")
        private double latitude;

        @DecimalMin("-180.0") @DecimalMax("180.0")
        private double longitude;

        @PositiveOrZero
        private double generationtime_ms;

        private int utc_offset_seconds;

        @NotBlank
        private String timezone;

        @NotBlank
        private String timezone_abbreviation;

        @PositiveOrZero
        private double elevation;

        @NotNull
        private CurrentWeatherUnits current_weather_units;

        @NotNull
        private CurrentWeather current_weather;

        @NotNull
        private HourlyUnits hourly_units;

        @NotNull
        private Hourly hourly;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurrentWeatherUnits {
        @NotBlank private String time;
        @NotBlank private String interval;
        @NotBlank private String temperature;
        @NotBlank private String windspeed;
        @NotBlank private String winddirection;
        @NotBlank private String is_day;
        @NotBlank private String weathercode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CurrentWeather {
        @NotBlank private String time;

        @PositiveOrZero
        private int interval;

        private double temperature;
        private double windspeed;
        private int winddirection;

        @Min(0) @Max(1)
        private int is_day;

        private int weathercode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HourlyUnits {
        @NotBlank private String time;
        @NotBlank private String pressure_msl;
        @NotBlank private String relative_humidity_2m;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hourly {

        @NotBlank private String time;
        @NotNull private Double pressure_msl;
        @NotNull private Integer relative_humidity_2m;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AirQuality {

        @PositiveOrZero
        private double pm10;

        @SerializedName("pm2_5")
        @PositiveOrZero
        private double pm2_5;

        @PositiveOrZero
        private double carbonMonoxide;

        @PositiveOrZero
        private double nitrogenDioxide;

        @PositiveOrZero
        private double sulphurDioxide;

        @PositiveOrZero
        private double ozone;
    }
}
