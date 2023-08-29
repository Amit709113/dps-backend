package com.amit.dps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dps.entities.Category;
import com.amit.dps.entities.Gallery;
import com.amit.dps.exceptions.ResourceNotFoundException;
import com.amit.dps.payloads.GalleryDto;
import com.amit.dps.repositories.CategoryRepo;
import com.amit.dps.repositories.GalleryRepo;
import com.amit.dps.services.GalleryService;

@Service
public class GalleryServiceIImpl implements GalleryService {

	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private GalleryRepo galleryRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Override
	public GalleryDto createGallery(GalleryDto galleryDto,Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		
		Gallery gallery=this.modelmapper.map(galleryDto,Gallery.class);
		gallery.setCategory(category);
		Gallery savedGallery=this.galleryRepo.save(gallery);
		return this.modelmapper.map(savedGallery, GalleryDto.class);
	}

	@Override
	public GalleryDto updateGallery(GalleryDto galleryDto, Integer galleryId) {
		
		Gallery gallery=this.galleryRepo.findById(galleryId).orElseThrow(()-> new ResourceNotFoundException("image", "id", galleryId));
		if(galleryDto.getGalleryAlt()!=null) {
			gallery.setGalleryAlt(galleryDto.getGalleryAlt());
		}
		if(galleryDto.getGalleryCaption()!=null) {
			gallery.setGalleryCaption(galleryDto.getGalleryCaption());
		}
		if(galleryDto.getGalleryLink()!=null) {
			gallery.setGalleryLink(galleryDto.getGalleryLink());
		}
		Gallery updatedGallery= this.galleryRepo.save(gallery);
		
		return this.modelmapper.map(updatedGallery, GalleryDto.class);
	}

	@Override
	public GalleryDto getGalleryById(Integer galleryId) {
		Gallery gallery=this.galleryRepo.findById(galleryId).orElseThrow(()-> new ResourceNotFoundException("image", "id", galleryId));
		return this.modelmapper.map(gallery, GalleryDto.class);
	}

	@Override
	public List<GalleryDto> getAllGallery() {
		// TODO Auto-generated method stub
		List<Gallery> list=this.galleryRepo.findAll();
		return list.stream().map((gallery)-> this.modelmapper.map(gallery, GalleryDto.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteGallery(Integer galleryId) {
		Gallery gallery=this.galleryRepo.findById(galleryId).orElseThrow(()-> new ResourceNotFoundException("image", "id", galleryId));
		this.galleryRepo.delete(gallery);

	}
	@Override
	public List<GalleryDto> findAllGalleryByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
        List<Gallery> list = this.galleryRepo.findByCategory(category);
        return list.stream().map((gallery)->this.modelmapper.map(gallery, GalleryDto.class)).collect(Collectors.toList());	
	}
}
