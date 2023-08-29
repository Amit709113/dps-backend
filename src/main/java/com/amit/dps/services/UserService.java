package com.amit.dps.services;

import java.util.List;

import com.amit.dps.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer Id);
	void deleteUser(Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	

}
