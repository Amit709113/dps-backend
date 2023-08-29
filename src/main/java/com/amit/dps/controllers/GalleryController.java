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
import com.amit.dps.payloads.GalleryDto;
import com.amit.dps.services.GalleryService;

@RestController
@RequestMapping("api/gallery/")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
	@PostMapping("/category/{categoryId}")
	
	ResponseEntity<GalleryDto> createGallery(@RequestBody GalleryDto galleryDto,@PathVariable Integer categoryId){
		GalleryDto createdGallery = this.galleryService.createGallery(galleryDto,categoryId);
		return new ResponseEntity<GalleryDto>(createdGallery, HttpStatus.CREATED);
		
	}
	@PutMapping("/{galleryId}")
	ResponseEntity<GalleryDto> updateGallery(@RequestBody GalleryDto galleryDto,@PathVariable Integer galleryId){
		GalleryDto updatedGallery= this.galleryService.updateGallery(galleryDto, galleryId);
		return new ResponseEntity<GalleryDto>(updatedGallery,HttpStatus.ACCEPTED);
	}
	@GetMapping("/{galleryId}")
	ResponseEntity<GalleryDto> getGalleryById(@PathVariable Integer galleryId){
		GalleryDto galleryDto = this.galleryService.getGalleryById(galleryId);
		return new ResponseEntity<GalleryDto>(galleryDto,HttpStatus.OK);
	}
	@GetMapping("/")
	ResponseEntity<List<GalleryDto>> getAllGallery(){
		List<GalleryDto> list = (List<GalleryDto>) this.galleryService.getAllGallery();
		return new ResponseEntity<List<GalleryDto>>(list,HttpStatus.OK);
	}
	@DeleteMapping("/{galleryId}")
	ResponseEntity<ApiResponse> deleteGallery(@PathVariable Integer galleryId){
		this.galleryService.deleteGallery(galleryId);
		
		return new  ResponseEntity<ApiResponse>(new ApiResponse("Image is deleted successfully",true),HttpStatus.OK);
	}
	@GetMapping("category/{categoryId}")
	ResponseEntity<List<GalleryDto>> getAllImagesByCategory( @PathVariable Integer categoryId){
		List<GalleryDto> list = this.galleryService.findAllGalleryByCategory(categoryId);
		return new ResponseEntity<List<GalleryDto>>(list,HttpStatus.OK);
	}
}


