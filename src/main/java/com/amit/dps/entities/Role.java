package com.amit.dps.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

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
