package com.amit.dps.payloads;

import com.amit.dps.entities.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GalleryDto {

	private Integer galleryId;
	
	private String galleryLink;
	
	private String galleryAlt;
	
	private String galleryCaption;
	
	//private Category category;  //confusion to put heer or not mean in this class
	
	
	
	
}
