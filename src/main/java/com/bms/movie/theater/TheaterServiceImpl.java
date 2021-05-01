package com.bms.movie.theater;

import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {

	private final ConversionService conversionService;

	private final TheaterRepository theaterRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<TheaterDto> getTheaters(String region) {
		List<Theater> theaters = theaterRepository.findByCity(region);
		TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Theater.class));
		TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(TheaterDto.class));
		return (List<TheaterDto>) conversionService.convert(theaters, sourceType, targetType);
	}

	@Override
	public List<TheaterDto> searchTheaters(String region, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
