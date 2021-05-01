package com.bms.movie.movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

	@GetMapping("/movie/lang")
	public ResponseEntity<String> getMovie(String lang, String from, String to) {
		return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/movie")
	public ResponseEntity<String> getMovie(String name) {
		return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/movies/top")
	public ResponseEntity<String> getTopMovies() {
		return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
	}
}
