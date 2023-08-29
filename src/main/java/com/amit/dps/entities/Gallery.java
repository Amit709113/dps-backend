package com.amit.dps.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
