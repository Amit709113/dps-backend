package com.amit.dps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.dps.payloads.ApiResponse;
import com.amit.dps.payloads.TopperDto;
import com.amit.dps.services.TopperService;

@RestController
@RequestMapping("api/topper/")
public class TopperController {
	
	@Autowired
	private TopperService topperService;
	
	@PostMapping("/")
	ResponseEntity<TopperDto> createTopper(@RequestBody TopperDto topperDto){
		TopperDto topperDto2=this.topperService.createTopper(topperDto);
		return new ResponseEntity<TopperDto>(topperDto2,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{topperId}")
	ResponseEntity<TopperDto> updateTopper(@RequestBody  TopperDto topperDto,@PathVariable Integer topperId){
		TopperDto topperDto2=this.topperService.updateTopper(topperDto, topperId);
		return new ResponseEntity<TopperDto>(topperDto2,HttpStatus.OK);
	}
	
	@GetMapping("/{topperId}")
	ResponseEntity<TopperDto> getTopperById(@PathVariable Integer topperId){
		TopperDto topperDto=this.topperService.getTopperById(topperId);
		return new ResponseEntity<TopperDto>(topperDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	ResponseEntity<List<TopperDto>> getAllTopper(){
		List<TopperDto> topperDtos=this.topperService.getAllTopper();
		return new ResponseEntity<List<TopperDto>>(topperDtos,HttpStatus.OK);
	}
	
	@DeleteMapping("/{topperId}")
	ResponseEntity<ApiResponse> deleteTopper(@PathVariable Integer topperId){
		this.topperService.deleteTopper(topperId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Student is deleted successfully",true),HttpStatus.OK);
	}
	

}
