package com.example.demo.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Role;
import com.example.demo.Service.UserService;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.RoleDto;
import com.example.demo.payload.UserDto;

@RestController
@RequestMapping("/users")

public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper mapper;
	
	 
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date=new Date();
		formatter.format(date);
		userDto.setDate(date);
		userDto.setActive(true);
		userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		UserDto ud=this.userService.create(userDto);
		return new ResponseEntity<UserDto>(ud,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")   
	@PutMapping("/{userId}")
    public ResponseEntity<UserDto> update(@PathVariable int userId,@RequestBody UserDto ud) {
    	
    	      UserDto userdto=userService.update(ud, userId);
    	      
    	return new ResponseEntity<UserDto>(userdto,HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ADMIN')")   
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable int userId) {
		
	   userService.delete(userId);
	   
	  return new ResponseEntity<ApiResponse>(new ApiResponse("User is Deleted",true),HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ADMIN')") 
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAll() {
		
		List<UserDto> allList=userService.getAll();		
		return new ResponseEntity<List<UserDto>>(allList,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")   
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId) {
		      
			UserDto userdto=userService.getByUserId(userId);
		
		return new ResponseEntity<UserDto>(userdto,HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ADMIN')")   
	@GetMapping("email/{email}")
	public ResponseEntity<UserDto>getUserByEmail(@PathVariable String email){
		            UserDto emailfind=userService.getByEmailId(email);
		return new ResponseEntity<UserDto>(emailfind,HttpStatus.OK);
	}
	
	
	
}
