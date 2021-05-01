package com.bms.movie.movies;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "movie")
public class Movie {

	@Id
	private String id;
	private String name;
	private String lang;

}
