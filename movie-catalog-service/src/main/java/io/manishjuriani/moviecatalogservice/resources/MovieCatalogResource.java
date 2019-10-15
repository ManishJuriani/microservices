package io.manishjuriani.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.manishjuriani.moviecatalogservice.models.CatalogItem;
import io.manishjuriani.moviecatalogservice.models.Movie;
import io.manishjuriani.moviecatalogservice.models.Rating;
import io.manishjuriani.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
				
		// get all rated movie IDs
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId,UserRating.class);
		
		return ratings.getUserRating().stream().map(rating->{
			// For each movie ID, call movie info service and get details
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			
			// Put them all together
			return new CatalogItem(movie.getName(),"Desc",rating.getRating());
		})
		.collect(Collectors.toList());
		
	}
	
}
