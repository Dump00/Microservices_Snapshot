package com.cisco.moviecatalogservice.contoller;

import com.cisco.moviecatalogservice.model.CatalogItem;
import com.cisco.moviecatalogservice.model.Movie;
import com.cisco.moviecatalogservice.model.Rating;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        /**
         * 1. get all rated movieId
         * 2. for each movieId, call movie info service and get details
         * 3. put them all together
         */

        List<Rating> ratings = Arrays.asList(
          new Rating("m001", 4),
          new Rating("m002", 5),
          new Rating("m003", 2)
        );

        return ratings.stream().map(rating -> {
            //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            Movie movie = webClientBuilder
                    .build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(movie.getName(),"DESC" , rating.getRating());
        }).collect(Collectors.toList());

    }
}
