package com.cisco.moviecatalogservice.contoller;

import com.cisco.moviecatalogservice.model.CatalogItem;
import com.cisco.moviecatalogservice.model.Movie;
import com.cisco.moviecatalogservice.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @RequestMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        RestTemplate restTemplate = new RestTemplate();

        List<Rating> ratings = Arrays.asList(
          new Rating("001", 4),
          new Rating("002", 5),
          new Rating("003", 3)
        );

        return ratings.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId()
                            , Movie.class);
                    return new CatalogItem(movie.getName(), "test desc", rating.getRating());
                })
                .collect(Collectors.toList());

    }
}
