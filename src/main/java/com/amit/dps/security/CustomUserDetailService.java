package com.amit.dps.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amit.dps.entities.User;
import com.amit.dps.exceptions.ResourceNotFoundException;
import com.amit.dps.repositories.UserRepo;

public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from database 
		   
		User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user", "email", 0));
		
		return null;
	}

}
