package com.amit.dps;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amit.dps.config.AppConstants;
import com.amit.dps.entities.Role;
import com.amit.dps.repositories.RoleRepo;



// I am at main branch

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		System.out.println("hello school ");
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		 return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role1=new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName(AppConstants.ADMIN);
			
			
			Role role2=new Role();
			role2.setId(AppConstants.NORMAL_USER);
			role2.setName(AppConstants.NORMAL);
			
			List<Role> roles = List.of(role1,role2);
			
			List<Role> result=this.roleRepo.saveAll(roles);
		}catch(Exception error) {
			error.printStackTrace();
			
		}
	}
	
	
	
	
}
