package com.amit.dps.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.dps.payloads.ApiResponse;
import com.amit.dps.payloads.UserDto;
import com.amit.dps.services.UserService;

@RestController 
@RequestMapping("api/users/")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto ){
		UserDto newUser=userService.createUser(userDto);
		
		return new ResponseEntity<>(newUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateuser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
//		return new ResponseEntity<>(newUser,HttpStatus.OK);
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer id){
		UserDto returnedUserDto=this.userService.getUserById(id);
		return new ResponseEntity<>(returnedUserDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto> > getAllUser(){
		List<UserDto> list=this.userService.getAllUser();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('NORMAL')")  //role permission 
	@DeleteMapping("/{userId}")
	public  ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
		
		
		this.userService.deleteUser(userId);
		return new  ResponseEntity<ApiResponse>(new ApiResponse("user is deleted successfully",true),HttpStatus.OK);
	}
	
	
} 
