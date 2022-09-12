package com.example.moviecatalogservice.resources;

import com.example.moviecatalogservice.CatalogItem;
import com.example.moviecatalogservice.Movie;
import com.example.moviecatalogservice.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {
    //Autowired is a consumer. Spring will look for the bean that matches and try to consume
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        //We need the Ratings class from movies-data-services
        List<Rating> ratingList = Arrays.asList(new Rating("foo", 4), new Rating("Another movie", 1));

        return ratingList.stream().map(rating -> {
            Movie movies = restTemplate.getForObject("http://localhost:8084/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movies.getName(), "some description", rating.getRating());
        }).collect(Collectors.toList());

/*
        return Collections.singletonList(new CatalogItem("Titanic", "test", 3));
*/
    }
}
