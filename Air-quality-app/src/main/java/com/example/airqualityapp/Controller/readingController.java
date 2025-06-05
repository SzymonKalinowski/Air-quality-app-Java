package com.example.airqualityapp.Controller;

import com.example.airqualityapp.Model.Reading;
import com.example.airqualityapp.Model.ReadingClosestTimestamp;
import com.example.airqualityapp.Service.readingService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readings")
public class readingController {

    public final readingService service;

    public readingController(readingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> receiveReading(@RequestBody String payload) {
        try {
            Gson gson = new Gson();
            Reading reading = gson.fromJson(payload, Reading.class);
            service.setReading(reading);
            return ResponseEntity.ok("Parsed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<ReadingClosestTimestamp> getClosestReadingToTimestamp() {
        try {
            Reading storedReading = service.getLastReceivedReading();

            ReadingClosestTimestamp closest = service.getClosestToTimestamp();
            return ResponseEntity.ok(closest);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/closest/{timestamp}")
    public ResponseEntity<ReadingClosestTimestamp> getClosestReadingToCustomTimestamp(@PathVariable String timestamp) {
        try {
            ReadingClosestTimestamp result = service.getClosestToGivenData(timestamp);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
