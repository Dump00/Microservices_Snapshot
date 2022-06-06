package com.cisco.movieinfoservice.controller;

import com.cisco.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @RequestMapping("{movieId}")
    private Movie getMovieInfo(@PathVariable String movieId){
        return new Movie(movieId, "test movie name");
    }
}
