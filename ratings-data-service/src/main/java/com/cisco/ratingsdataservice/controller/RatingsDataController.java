package com.cisco.ratingsdataservice.controller;

import com.cisco.ratingsdataservice.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataController {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId){
        return new Rating(movieId, 4);
    }
}
