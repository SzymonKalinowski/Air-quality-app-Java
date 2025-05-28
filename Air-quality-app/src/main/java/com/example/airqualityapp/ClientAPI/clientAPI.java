package com.example.airqualityapp.ClientAPI;

import java.io.IOException;
import java.net.http.*;
import java.net.URI;

import com.google.gson.*;

public class clientAPI {
    private static String latitude = "52.52";
    private static String longitude = "13.41";

    private static String name = "localhost";
    private static Integer port = 8080;


    private static final Gson gson = new Gson();

    public static JsonObject fetchWeather() throws IOException, InterruptedException {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=52.2297&longitude=21.0122&current_weather=true&hourly=pressure_msl,relative_humidity_2m";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Api response: " + response.statusCode());
        }

        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    public static JsonObject fetchAirQuality(String latitude, String longitude) throws IOException, InterruptedException {
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
            throw new IOException("Api response: " + response.statusCode());
        }

        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    public static JsonObject sendToBackend(JsonObject reading, String backendName, Integer backendPort) throws IOException, InterruptedException {
        String url = String.format("http://%s:%d/readings", backendName, backendPort);
        String json = gson.toJson(reading);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject result = new JsonObject();
        if (response.statusCode() != 200) {
            throw new IOException("Backend response: " + response.statusCode());
        }
        result.add("body", JsonParser.parseString(response.body()));

        return result;
    }

    public static void main(String[] args) {
        try{
            JsonObject weather = fetchWeather();
            JsonObject data = fetchAirQuality(latitude,longitude);

            JsonObject hourly = data.getAsJsonObject("hourly");
            JsonArray times = hourly.getAsJsonArray("time");
            if (times == null || times.size() == 0) {
                System.err.println("No data sended");
                return;
            }

            String timestamp = times.get(0).getAsString();

            JsonObject airQuality = new JsonObject();
            airQuality.add("pm10", getFirstOrNull(hourly, "pm10"));
            airQuality.add("pm2_5", getFirstOrNull(hourly, "pm2_5"));
            airQuality.add("carbonMonoxide", getFirstOrNull(hourly, "carbon_monoxide"));
            airQuality.add("nitrogenDioxide", getFirstOrNull(hourly, "nitrogen_dioxide"));
            airQuality.add("sulphurDioxide", getFirstOrNull(hourly, "sulphur_dioxide"));
            airQuality.add("ozone", getFirstOrNull(hourly, "ozone"));

            JsonObject reading = new JsonObject();
            reading.addProperty("timestamp", timestamp);
            reading.add("weather", weather);
            reading.add("airQuality", airQuality);

            System.out.println("Sending to backend:\n" + gson.toJson(reading));

            JsonObject response = sendToBackend(reading, name, port);

            System.out.println("Backend response:" + gson.toJson(response));


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.err.println("Error:" + e.getMessage());
        }
    }

    private static JsonElement getFirstOrNull(JsonObject hourly, String key) {
        try {
            JsonArray array = hourly.getAsJsonArray(key);
            return array.size() > 0 ? array.get(0) : JsonNull.INSTANCE;
        } catch (Exception e) {
            return JsonNull.INSTANCE;
        }
    }

}