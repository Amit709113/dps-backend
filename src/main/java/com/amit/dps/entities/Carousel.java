package com.amit.dps.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="carousel")
@Getter
@Setter
@NoArgsConstructor
public class Carousel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int carouselId;
	
	private String carouselLink;
	
	private String carouselComment;
	
	private String carouselAlt;
	
}
