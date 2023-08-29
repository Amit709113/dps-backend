package com.amit.dps.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter


public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 3,message="username must be minimum of four character")
	private String name;
	
	@Email(message="invalid email address")
	private String email;
	
	
	private String password;
	
	@NotEmpty
	private String about;
	
//	private String role;

}
