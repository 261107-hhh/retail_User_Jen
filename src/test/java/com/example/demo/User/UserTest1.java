package com.example.demo.User;

//import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;

//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;
import java.util.Optional;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Before;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Controller.UserController;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
//import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.UserDto;

//import io.restassured.http.ContentType;
//import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
//import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;


//@PreAuthorize(("hasRole('ADMIN')") )
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class UserTest1 {
	
	@Autowired
	private UserController userController;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
//	@Before(value = "")
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}
	
	static 	int id ;
	UserDto user;
	String mail = "test@example.com";
	
	
	@Test
	@Order(1)
	public void testCreateUser() {
		// Prepare test data

		user = new UserDto();
		user.setName("testuser");
		user.setPassword("password");
		user.setEmail(mail);
		Date currentDate = new Date();
		user.setDate(currentDate);
		user.setActive(true);
		
		
		// Perform the call
		ResponseEntity<UserDto> response = userController.createUser(user);

		
		// Verify the response
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(mail, response.getBody().getEmail());
		id = response.getBody().getUserId();
		System.out.println(id +" this is id");

	}


	@Test
	@Order(2)
	public void testUpdateUser() {
	// Prepare test data
		UserDto updatedUserDto = new UserDto();
		updatedUserDto.setUserId(id);
		updatedUserDto.setName("updateduser");
		updatedUserDto.setPassword("hashedNewPassword");
		updatedUserDto.setEmail("updated@example.com");
	

	// Perform the call
		UserDto response = userService.update(updatedUserDto, id);
	
	// Verify the response
		assertEquals("updated@example.com", response.getEmail());
		assertEquals(id, response.getUserId());
	}

	@Test
	@Order(3)
	public void testDeleteUser() {
	
		// Perform the call
		
		userService.delete(id);
	
		// Verify the response
		Optional<User> res = userRepository.findByEmail(mail);
		assertThat(res).isEmpty();
		
	}
	
//	 @Test
//	 @Order(4)
//	    public void testGetAll() throws JSONException {
//	    	 String jsonBody="{ \"username\": \"himanshu.nainwal@stl.tech\", \"password\":\"123\"}";
//	    	 String tokenn =  given()
//	               .header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
//	               .body(jsonBody)
//	               .when()
//	               .post("http://localhost:9001/auth/login")
//	               .then().assertThat().statusCode(equalTo(200)).extract().response().asString();
//	       
//	       System.out.println("Hi there Token"+ tokenn);
//	       
//	       JSONObject json;
//	       json = new JSONObject(tokenn);
//	       
//	               
//	        System.out.println(json.getString("token"));
//	    
//	       
//	       String result = given()
//	             .header("Authorization", "Bearer " + json.getString("token")).contentType(ContentType.JSON).accept(ContentType.JSON)
//	             .when()
//	             .get("http://localhost:9001/users/")
//	             .then()
//	             .assertThat().statusCode(equalTo(200))
//	             .extract().response().asString();
//	    	System.out.println("Hi this is result "+ result);
//
//	    	JSONArray jj = new JSONArray(result);
//	    	String d = jj.getString(0);
//	    	JSONObject jjo = new JSONObject(d);
//	    	String jEmail = jjo.getString("email");
//	    	
//	    	List<UserDto> res = userService.getAll();
//	    	String email = res.get(0).getEmail();
//	    	System.out.println(jj.get(0));
//	    	System.out.println(email);
//	    	assertEquals(email, jEmail);
//	       
//	    }
	
//
	@Test
	@Order(5)
	public void testGetUserById() {

		int userId = 1;
		String mail = "himanshu.nainwal@stl.tech";

		UserDto response = userService.getByUserId(userId);
	
		assertEquals(response.getEmail(), mail);
	}
//
	@Test
	@Order(6)	
	public void testGetUserByEmail() {
	// Prepare test data

		String mail = "himanshu.nainwal@stl.tech";
		
	// Perform the call
		UserDto response = userService.getByEmailId(mail);

		assertEquals(response.getEmail(), mail);
		
		
//		ResponseEntity<UserDto> res = userController.getUserByEmail(mail);
		
	}
	
}