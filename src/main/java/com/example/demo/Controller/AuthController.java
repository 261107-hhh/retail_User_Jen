package com.example.demo.Controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.UserDto;
import com.example.demo.Exception.BadUserLoginDetailsException;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.imp.CustomUserDetailsService;
import com.example.demo.payload.JwtRequest;
import com.example.demo.payload.JwtResponse;
import com.example.demo.security.JwtHelper;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
	@Autowired
	private AuthenticationManager manger;
	@Autowired
	private CustomUserDetailsService userDrtailsservice;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtHelper jwthelper;
	@Autowired
	private ModelMapper mapper;
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)throws Exception{
		
		this.authenticateUser(request.getUsername(),request.getPassword());
		
		UserDetails UserDetail = this.userDrtailsservice.loadUserByUsername(request.getUsername());
		userRepository.findByEmail(UserDetail.getUsername());
		System.out.println(UserDetail.getUsername()+" User Name");
		Optional<User> user = userRepository.findByEmail(UserDetail.getUsername());
		if(!user.get().isActive()) {
			System.out.println("Not an active user");
			throw new BadUserLoginDetailsException("User is Not Active");
		}
		/*  
		 * if username and password both are Correct then get token by line no *47
		 * create jwtResponse(this class created by my) object
		 * Under JwtResponse class there is entity token
		 * 
		 */
		
		String token = this.jwthelper.generateToken(UserDetail);
		
		JwtResponse response =new JwtResponse();
		response.setToken(token);
		response.setUser(this.mapper.map(UserDetail,UserDto.class));
		return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
	}
	

	//Authenticate
	private void authenticateUser(String username, String password) {
		try {
		manger.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(BadCredentialsException e){
			throw new BadUserLoginDetailsException("Invaild Username or Password");
			
		}catch(DisabledException e) {
			throw new BadUserLoginDetailsException("User is disable");
		}
		}

}
