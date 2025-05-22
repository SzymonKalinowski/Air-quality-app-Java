package com.example.airqualityapp.Controller;

import org.springframework.web.bind.annotation.*;

import org.json.*;

@RestController
@RequestMapping("/readings")
public class readingController {

    @GetMapping
    public String postReadings() {
        return "This shit will appear here";
    }

    @GetMapping("/nearest")
    public String getNearest() {
        return "Some shit";
    }


}
