package com.amit.dps.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="gallery_table")
@Getter
@Setter
@NoArgsConstructor
public class Gallery {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)  //to be careful here
	private Integer galleryId;
	
	private String galleryLink;
	
	private String galleryAlt;
	
	private String galleryCaption;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

}
