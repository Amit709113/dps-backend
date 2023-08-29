package com.amit.dps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dps.entities.Topper;
import com.amit.dps.exceptions.ResourceNotFoundException;
import com.amit.dps.payloads.TopperDto;
import com.amit.dps.repositories.TopperRepo;
import com.amit.dps.services.TopperService;


@Service
public class TopperServiceImpl implements TopperService {

	@Autowired
	private TopperRepo topperRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public TopperDto createTopper(TopperDto topperDto) {
		Topper topper=this.modelMapper.map(topperDto, Topper.class);
		Topper savedTopper=this.topperRepo.save(topper);
		
		
		return this.modelMapper.map(savedTopper, TopperDto.class);
	}

	@Override
	public TopperDto updateTopper(TopperDto topperDto, Integer topperId) {
		Topper topper = this.topperRepo.findById(topperId).orElseThrow(()->new ResourceNotFoundException("student", "id", topperId));
		
		if(topperDto.getTopperFeedback()!=null) {
			topper.setTopperFeedback(topperDto.getTopperFeedback());
		}
		if(topperDto.getTopperLink()!=null) {
			topper.setTopperLink(topperDto.getTopperLink());
		}
		if(topperDto.getTopperName()!=null) {
			topper.setTopperName(topperDto.getTopperName());
		}
		if(topperDto.getTopperScore()!=null) {
			topper.setTopperScore(topperDto.getTopperScore());
		}
		if(topperDto.getTopperYear()!=null) {
			topper.setTopperYear(topperDto.getTopperYear());
		}
		
		Topper updatedTopper=this.topperRepo.save(topper);
		
		
		return this.modelMapper.map(updatedTopper, TopperDto.class);
	}

	@Override
	public TopperDto getTopperById(Integer topperId) {
		Topper topper = this.topperRepo.findById(topperId).orElseThrow(()->new ResourceNotFoundException("student", "id", topperId));
		
		
		return this.modelMapper.map(topper, TopperDto.class);
	}

	@Override
	public List<TopperDto> getAllTopper() {
		List<Topper> list = this.topperRepo.findAll();
		List<TopperDto> topperDtos = list.stream().map((topper)->this.modelMapper.map(topper, TopperDto.class)).collect(Collectors.toList());
		return topperDtos;
	}

	@Override
	public void deleteTopper(Integer topperId) {
		Topper topper = this.topperRepo.findById(topperId).orElseThrow(()->new ResourceNotFoundException("student", "id", topperId));
		
		this.topperRepo.delete(topper);

	}

}
