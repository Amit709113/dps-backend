package com.amit.dps.entities;

import java.util.HashSet;
import java.util.Set;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="user_name",nullable=false,length=100)
	private String name;
	
	@Email
	private String email; 
	private String about;
	private String password;
//	private String role;
	
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id") 
	)
	private Set<Role> roles=new HashSet<>();
	

}
