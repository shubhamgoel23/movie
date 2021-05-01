package com.bms.movie.theater;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends MongoRepository<Theater, String> {

	List<Theater> findByName(String name);

	List<Theater> findByCity(String city);

}
