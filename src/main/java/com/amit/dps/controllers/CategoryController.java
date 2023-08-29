package com.amit.dps.controllers;

import java.util.ArrayList;
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
import com.amit.dps.payloads.CategoryDto;
import com.amit.dps.payloads.GalleryDto;
import com.amit.dps.services.CategoryService;

@RestController
@RequestMapping("api/category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/")
	ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto categoryDto2 = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{categoryId}")
	ResponseEntity<CategoryDto> getByCategoryId(@PathVariable Integer categoryId){
		CategoryDto categroyById = this.categoryService.getCategroyById(categoryId);
		return new ResponseEntity<CategoryDto>(categroyById,HttpStatus.OK);
	}
	
	@GetMapping("/")
	ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> dtos=this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(dtos, HttpStatus.OK);
		
	}
	@DeleteMapping("/{categoryId}")
	ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",true),HttpStatus.OK);
	}
	@GetMapping("/images/{categoryId}")
	ResponseEntity<List<GalleryDto>> getAllImageByCategory(@PathVariable Integer categoryId){
//		System.out.println(categoryId);
		List<GalleryDto> allGallery = this.categoryService.getAllGallery(categoryId);
		return new ResponseEntity<List<GalleryDto>>(allGallery,HttpStatus.OK);
		
	}

}
