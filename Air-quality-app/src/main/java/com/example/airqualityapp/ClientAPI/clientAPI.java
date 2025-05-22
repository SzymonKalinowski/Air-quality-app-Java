package com.example.airqualityapp.ClientAPI;

import java.io.IOException;
import java.net.http.*;
import java.net.URI;
import org.json.*;

public class clientAPI {
    private static String latitude = "52.52";
    private static String longitude = "13.41";

    private static String name = "localhost";
    private static String port = "8080";


    public static JSONObject fetchAirQuality(String latitude, String longitude) throws IOException, InterruptedException {
        String url = String.format(
                "https://air-quality-api.open-meteo.com/v1/air-quality?latitude=%s&longitude=%s&hourly=pm10,pm2_5,carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone&timezone=Europe%%2FWarsaw",
                latitude, longitude
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("API status " + response.statusCode());
        }

        return new JSONObject(response.body());
    }
}