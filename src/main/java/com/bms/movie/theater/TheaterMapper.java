package com.bms.movie.theater;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.bms.movie.config.MapperCustomConfig;

@Mapper(config = MapperCustomConfig.class)
public interface TheaterMapper extends Converter<Theater, TheaterDto> {

	@Override
	TheaterDto convert(Theater theater);

}
