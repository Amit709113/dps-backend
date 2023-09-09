package com.amit.dps.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amit.dps.config.AppConstants;
import com.amit.dps.entities.Role;
import com.amit.dps.entities.User;
import com.amit.dps.exceptions.ResourceNotFoundException;
import com.amit.dps.payloads.UserDto;
import com.amit.dps.repositories.RoleRepo;
import com.amit.dps.repositories.UserRepo;
import com.amit.dps.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private RoleRepo roleRepo;

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
		user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		
		userRepo.save(user);
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public void deleteUser(Integer id) {
		if(id==101) return;
 		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user", "id", id));
 		user.setRoles(null);
 		userRepo.save(user);
 		//wait here
		userRepo.delete(user);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user", "id", id));
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		System.out.println(userDto.getPassword());
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users =this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		User save = this.userRepo.save(user);
		
		return this.modelMapper.map(save, UserDto.class);
	}

}
