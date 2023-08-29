package com.amit.dps.services;

import java.util.List;

import com.amit.dps.payloads.GalleryDto;

public interface GalleryService {

	GalleryDto createGallery(GalleryDto galleryDto, Integer categoryId);
	
	GalleryDto updateGallery(GalleryDto galleryDto,Integer galleryId);
	
	GalleryDto getGalleryById(Integer galleryId);
	
	List<GalleryDto> getAllGallery();
	
	void deleteGallery(Integer galleryId);
	
	List<GalleryDto> findAllGalleryByCategory( Integer CategoryId);
}
