package com.amit.dps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.dps.entities.Carousel;

public interface CarouselRepo extends JpaRepository<Carousel, Integer> {

}
