package com.amit.dps.services;

import java.util.List;



import com.amit.dps.payloads.CategoryDto;
import com.amit.dps.payloads.GalleryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	CategoryDto getCategroyById(Integer categoryId);
	List<CategoryDto> getAllCategory();
	
    List<GalleryDto> getAllGallery(Integer categoryId);
	void deleteCategory(Integer categoryId);

}
