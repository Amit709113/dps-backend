package com.amit.dps.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
