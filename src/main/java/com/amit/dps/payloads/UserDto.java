package com.amit.dps.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import com.amit.dps.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<Role> roles=new HashSet<>();
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}


}
