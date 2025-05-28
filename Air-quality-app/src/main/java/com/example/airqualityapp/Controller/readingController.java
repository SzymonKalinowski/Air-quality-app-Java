package com.example.airqualityapp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/readings")
public class readingController {

    @PostMapping
    public ResponseEntity<String> receiveReading(@RequestBody String payload) {
        try {
            JsonObject reading = JsonParser.parseString(payload).getAsJsonObject();
            System.out.println("Recived: " + reading.toString());
            return ResponseEntity.ok(reading.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON: " + e.getMessage());
        }
    }
}
