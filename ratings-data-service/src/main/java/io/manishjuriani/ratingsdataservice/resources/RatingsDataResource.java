package io.manishjuriani.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.manishjuriani.ratingsdataservice.models.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/users/{userId}")
	public List<Rating> getUserRating(@PathVariable String userId){
		return Arrays.asList(
				new Rating("1234",4),
				new Rating("5678",3)
		);
	}
	
}
