package com.amit.dps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dps.entities.Carousel;
import com.amit.dps.exceptions.ResourceNotFoundException;
import com.amit.dps.payloads.CarouselDto;
import com.amit.dps.repositories.CarouselRepo;
import com.amit.dps.services.CarouselService;

@Service
public class CarouselServiceImpl implements CarouselService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CarouselRepo carouselRepo;
	
	@Override
	public CarouselDto createCarousel(CarouselDto carouselDto) {
		Carousel carousel=this.modelMapper.map(carouselDto, Carousel.class);
		Carousel savedCarousel=this.carouselRepo.save(carousel);
		
		return this.modelMapper.map(savedCarousel, CarouselDto.class);
	}

	@Override
	public CarouselDto updateCarousel(Integer carouselId, CarouselDto carouselDto) {
		Carousel carousel = this.carouselRepo.findById(carouselId).orElseThrow(()->new ResourceNotFoundException("carousel", "carousel Id ", carouselId));
		if(carouselDto.getCarouselAlt()!=null) {
			carousel.setCarouselAlt(carouselDto.getCarouselAlt());
		}
		if(carouselDto.getCarouselLink()!=null) {
			carousel.setCarouselLink(carouselDto.getCarouselLink());
		}
		if(carouselDto.getCarouselComment()!=null) {
			carousel.setCarouselComment(carouselDto.getCarouselComment());
		}
		Carousel updatedCarousel = this.carouselRepo.save(carousel);
		
		return this.modelMapper.map(updatedCarousel,CarouselDto.class);
	}

	@Override
	public CarouselDto getCarouselById(Integer carouselId) {
		Carousel carousel = this.carouselRepo.findById(carouselId).orElseThrow(()->new ResourceNotFoundException("carousel", "carousel Id ", carouselId));
		
		return this.modelMapper.map(carousel,CarouselDto.class);
	}

	@Override
	public List<CarouselDto> getAllCarousel() {
		List<Carousel> list=this.carouselRepo.findAll();
		List<CarouselDto> carouselDtos=list.stream().map((carousel)->this.modelMapper.map(carousel, CarouselDto.class)).collect(Collectors.toList());

		return carouselDtos;
	}

	@Override
	public void deleteCarouel(Integer carouselId) {
		Carousel carousel = this.carouselRepo.findById(carouselId).orElseThrow(()->new ResourceNotFoundException("carousel", "carousel Id ", carouselId));
		
		this.carouselRepo.delete(carousel);
		
	}
	

}
