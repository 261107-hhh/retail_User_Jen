package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Model.Role;
import com.example.demo.Repository.RoleRepository;
//import com.ecom.Model.Role;
//import com.ecom.Repository.RoleRepository;

@SpringBootApplication
public class UserMicroserviceApplication  implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
	
	@Bean
	public ModelMapper mapper() {
		
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role0=new Role();
			role0.setId(5245);
			role0.setName("ROLE_ADMIN");
			
			Role role1=new Role();
			role1.setId(7412);
			role1.setName("ROLE_NORMAL");
			
			
			//this.roleRepository.saveAll(List.of(role1,role2,role3));//this Feather to words 9
			
			//orelse use
			 // Create Array List 
			  //add all Role in array List
			  //use Save All method Under roleRepository
			  //For Eg :-
			  List<Role> role=new ArrayList<>();
			  role.add(role0);
			  role.add(role1);
			 this.roleRepository.saveAll(role);
			  

			
		}catch(Exception e) {
			
			System.out.println("User already exist");
			e.printStackTrace();
			
		}
		
	}

}
