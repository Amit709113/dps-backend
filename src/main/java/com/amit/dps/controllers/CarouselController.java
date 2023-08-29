package com.amit.dps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.dps.payloads.ApiResponse;
import com.amit.dps.payloads.CarouselDto;
import com.amit.dps.services.CarouselService;

@RestController
@RequestMapping("api/carousel/")
public class CarouselController {
	
	@Autowired
	private CarouselService carouselService;
	
	@PostMapping("/")
	public ResponseEntity<CarouselDto> createCarousel(@RequestBody CarouselDto carouselDto){
		CarouselDto createdCarousel = this.carouselService.createCarousel(carouselDto);
		return new ResponseEntity<CarouselDto>(createdCarousel,HttpStatus.CREATED);
	}
	
	@PutMapping("/{carouselId}")
	public ResponseEntity<CarouselDto> updatedCarousel(@RequestBody CarouselDto carouselDto,@PathVariable Integer carouselId){
		CarouselDto updatedCarousel=this.carouselService.updateCarousel(carouselId, carouselDto);
		return new ResponseEntity<CarouselDto>(updatedCarousel,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{carouselId}")
	public ResponseEntity<CarouselDto> getCarouselById(@PathVariable Integer carouselId){
		CarouselDto carouselDto=this.carouselService.getCarouselById(carouselId);
		return new ResponseEntity<CarouselDto>(carouselDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CarouselDto>> getAllCarousel(){
		List<CarouselDto> list = this.carouselService.getAllCarousel();
		return new ResponseEntity<List<CarouselDto>>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/{carouselId}")
	public ResponseEntity<ApiResponse> deleteCarousel(@PathVariable Integer carouselId){
		this.carouselService.deleteCarouel(carouselId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("carousel is deleted successfully",true),HttpStatus.OK);
	}

}
