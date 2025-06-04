package com.example.airqualityapp.Controller;

import com.example.airqualityapp.Model.Reading;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/readings")
public class readingController {



    @PostMapping
    public ResponseEntity<String> receiveReading(@RequestBody String payload) {
        try {
            Gson gson = new Gson();
            Reading reading = gson.fromJson(payload, Reading.class);
            System.out.println(reading);
            return ResponseEntity.ok("Parsed successfully");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON: " + e.getMessage());
        }
    }
}
