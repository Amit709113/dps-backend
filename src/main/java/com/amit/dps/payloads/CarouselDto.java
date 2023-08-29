package com.amit.dps.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarouselDto {

	private int carouselId;
	
	private String carouselLink;
	
	private String carouselAlt;
	
	private String carouselComment;
	
	
}
