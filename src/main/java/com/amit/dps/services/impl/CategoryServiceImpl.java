package com.amit.dps.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dps.entities.Category;
import com.amit.dps.entities.Gallery;
import com.amit.dps.exceptions.ResourceNotFoundException;
import com.amit.dps.payloads.CategoryDto;
import com.amit.dps.payloads.GalleryDto;
import com.amit.dps.repositories.CategoryRepo;
import com.amit.dps.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		this.categoryRepo.save(category);
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		if(categoryDto.getCategoryName()!=null) {
			category.setCategoryName(categoryDto.getCategoryName());
		}
		if(categoryDto.getCategoryAbout()!=null) {
			category.setCategoryAbout(categoryDto.getCategoryAbout());
		}
		this.categoryRepo.save(category);
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategroyById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> list= this.categoryRepo.findAll();
		
		List<CategoryDto> list2 = list.stream().map((category)->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return list2;
	}

	//to be checked separately
	@Override
	public List<GalleryDto> getAllGallery(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		Set<Gallery> galleries= new HashSet<Gallery>(category.getGalleries());
		List<Gallery> list=new ArrayList<>();
		galleries.forEach((gallery)->list.add(gallery));
		return  list.stream().map((gallery)-> this.modelMapper.map(gallery, GalleryDto.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		this.categoryRepo.delete(category);
		
		
	}

}
