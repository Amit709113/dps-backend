package com.amit.dps.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Role {
	
	@Id 
	private int id;
	
	private String name;
	

}
