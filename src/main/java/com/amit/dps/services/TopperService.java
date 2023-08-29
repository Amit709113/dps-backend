package com.amit.dps.services;

import java.util.List;

import com.amit.dps.payloads.TopperDto;

public interface TopperService {
	//cred
	
	TopperDto createTopper(TopperDto topperDto);
	TopperDto updateTopper(TopperDto topperDto,Integer topperId);
	TopperDto getTopperById(Integer topperId);
	List<TopperDto> getAllTopper();
	void deleteTopper(Integer topperId);

}
