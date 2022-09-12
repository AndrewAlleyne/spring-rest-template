package com.example.moviedataservice.resoruces;

import com.example.moviedataservice.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@ComponentScan(basePackages = "")

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

@RequestMapping("/{movieId}")
public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId,4);
    }
}
