package com.bms.movie.seat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {
	
	@GetMapping("/seat")
	public ResponseEntity<String> getAvailableSeats(String show) {
		return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/seat/book")
	public ResponseEntity<String> bookSeat(String name) {
		return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
	}

}
