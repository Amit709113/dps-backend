package com.amit.dps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.dps.entities.Category;
import com.amit.dps.entities.Gallery;

public interface GalleryRepo extends JpaRepository<Gallery, Integer> {
	
	List<Gallery> findByCategory(Category category);
	
}
