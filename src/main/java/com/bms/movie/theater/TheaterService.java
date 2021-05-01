package com.bms.movie.theater;

import java.util.List;

public interface TheaterService {

	List<TheaterDto> getTheaters(String region);

	List<TheaterDto> searchTheaters(String region, String name);

}
