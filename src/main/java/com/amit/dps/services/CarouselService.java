package com.amit.dps.services;

import java.util.List;

import com.amit.dps.payloads.CarouselDto;

public interface CarouselService {

	CarouselDto createCarousel(CarouselDto carouselDto);
	CarouselDto updateCarousel(Integer carouselId,CarouselDto carouselDto );
	CarouselDto getCarouselById(Integer carouselId);
	List<CarouselDto> getAllCarousel();
	void deleteCarouel(Integer id);
	//try to implement delete all 
}
