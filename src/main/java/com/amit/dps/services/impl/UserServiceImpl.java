package com.amit.dps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dps.entities.User;
import com.amit.dps.exceptions.ResourceNotFoundException;
import com.amit.dps.payloads.UserDto;
import com.amit.dps.repositories.UserRepo;
import com.amit.dps.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override 
	public UserDto createUser(UserDto userDto) {
		
		User user=this.modelMapper.map(userDto, User.class);
		userRepo.save(user);
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer Id) {
		User user = this.userRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("user", "id", Id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		userRepo.save(user);
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public void deleteUser(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user", "id", id));
		userRepo.delete(user);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user", "id", id));
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users =this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

}
