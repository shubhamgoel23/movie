package com.bms.movie.movies;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "movieTime")
public class MovieTime {

	@Id
	private String id;
	@DBRef
	private Movie movie;
	private String from;
	private String to;

}
