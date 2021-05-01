package com.bms.movie.seat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bms.movie.movies.MovieTime;
import com.bms.movie.theater.Theater;

import lombok.Data;

@Data
@Document(collection = "seat")
public class Seat {

	@Id
	private String id;
	private String no;
	private Theater theater;
	private MovieTime movieTime;
	private String status;
	private String bookedTime;

}
