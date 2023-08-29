package com.amit.dps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.dps.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	
}
