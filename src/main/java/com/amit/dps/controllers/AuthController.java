package com.amit.dps.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.dps.entities.User;
import com.amit.dps.exceptions.ApiException;
import com.amit.dps.payloads.JwtAuthRequest;
import com.amit.dps.payloads.JwtAuthResponse;
import com.amit.dps.payloads.UserDto;
import com.amit.dps.security.JwtTokenHelper;
import com.amit.dps.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
//@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@Valid
			@RequestBody JwtAuthRequest request
			) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		response.setUserDto(this.modelMapper.map((User)userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	 
	}
	
	//register user
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
		
		UserDto register = this.userService.registerUser(userDto);
		return new ResponseEntity<UserDto>(register,HttpStatus.CREATED);
		
	}
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			this.authenticationManager.authenticate(authenticationToken);
			
		}catch(BadCredentialsException e) {
			System.out.println("invalid details");
			throw new ApiException("invalid credentials");
			 
		}
		//we will handle exception globally
	}

}
