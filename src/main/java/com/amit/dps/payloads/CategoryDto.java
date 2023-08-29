package com.amit.dps.payloads;

import java.util.HashSet;
import java.util.Set;

import com.amit.dps.entities.Gallery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	
	private String categoryName;
	
	private String categoryAbout;
	
//	private Set<Gallery> galleries=new HashSet<>();
	
}
