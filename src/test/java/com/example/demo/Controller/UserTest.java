package com.example.demo.Controller;
//package com.example.demo.User;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Before;
//import org.junit.FixMethodOrder;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.example.demo.Controller.UserController;
//import com.example.demo.Model.User;
//import com.example.demo.Repository.UserRepository;
//import com.example.demo.Service.UserService;
//import com.example.demo.payload.ApiResponse;
//import com.example.demo.payload.UserDto;
//
//import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;
//
//
////  REST CALL TEST //
//
////@RunWith(SpringRunner.class)
////@AutoConfigureTestDatabase(replace=Replace.NONE)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//public class UserTest {
//	
//	@Mock
//	private UserService userService;
//	
//	@Mock
//	private PasswordEncoder passwordEncoder;
//	
//	@Mock
//	private ModelMapper modelMapper;
//	
//	@InjectMocks
//	private UserController userController;
//	
//	@Before(value = "")
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void testCreateUser() {
//		// Prepare test data
//		UserDto userDto = new UserDto();
//		userDto.setName("testuser");
//		userDto.setPassword("password");
//		userDto.setEmail("test@example.com");
//		Date currentDate = new Date();
//		userDto.setDate(currentDate);
//		userDto.setActive(true);
//		
//		UserDto savedUserDto = new UserDto();
//		savedUserDto.setUserId(1);
//		savedUserDto.setName("testuser");
//		savedUserDto.setPassword("hashedPassword");
//		savedUserDto.setEmail("test@example.com");
//		savedUserDto.setDate(currentDate);
//		savedUserDto.setActive(true);
//		
//		 // Mock dependencies' behavior
//		when(passwordEncoder.encode(userDto.getPassword())).thenReturn("hashedPassword");
//		when(userService.create(any(UserDto.class))).thenReturn(savedUserDto);
//		
//		// Perform the API call
//		ResponseEntity<UserDto> response = userController.createUser(userDto);
//		
//		// Verify the response
//		assertEquals(HttpStatus.CREATED, response.getStatusCode());
//		assertEquals(savedUserDto.getUserId(), response.getBody().getUserId());
//	}
//
//	@Test
//	public void testUpdateUser() {
//	// Prepare test data
//		int userId = 1;
//		UserDto userDto = new UserDto();
//		userDto.setName("updateduser");
//		userDto.setPassword("newpassword");
//		userDto.setEmail("updated@example.com");
//	
//		UserDto updatedUserDto = new UserDto();
//		updatedUserDto.setUserId(userId);
//		updatedUserDto.setName("updateduser");
//		updatedUserDto.setPassword("hashedNewPassword");
//		updatedUserDto.setEmail("updated@example.com");
//	
//	// Mock dependencies' behavior
//		when(userService.update(userDto,userId)).thenReturn(updatedUserDto);
//		when(passwordEncoder.encode(userDto.getPassword())).thenReturn("hashedNewPassword");
//	
//	// Perform the API call
//		ResponseEntity<UserDto> response = userController.update(userId, userDto);
//	
//	// Verify the response
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(updatedUserDto, response.getBody());
//	}
//
//	@Test
//	public void testDeleteUser() {
//	// Prepare test data
//		int userId = 1;
//	
//	// Mock dependencies' behavior
//		doNothing().when(userService).delete(userId);
//	
//	// Perform the API call
//		ResponseEntity<ApiResponse> response = userController.delete(userId);
//	
//	// Verify the response
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals("User is Deleted", response.getBody().getMessage());
//		assertTrue(response.getBody().isSuccess());
//	}
//
//	@Test
//	public void testGetAllUsers() {
//	// Prepare test data
//		List<UserDto> userList = new ArrayList<>();
//		userList.add(new UserDto(1, "user1", "password1", "user1@example.com", new Date(), true));
//		userList.add(new UserDto(2, "user2", "password2", "user2@example.com", new Date(), true));
//	
//	// Mock dependencies' behavior
//		when(userService.getAll()).thenReturn(userList);
//	
//	// Perform the API call
//		ResponseEntity<List<UserDto>> response = userController.getAll();
//	
//	// Verify the response
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(userList, response.getBody());
//	}
//
//	@Test
//	public void testGetUserById() {
//	// Prepare test data
//		int userId = 1;
//		UserDto userDto = new UserDto(userId, "testuser", "password", "test@example.com", new Date(), true);
//	
//	// Mock dependencies' behavior
//		when(userService.getByUserId(userId)).thenReturn(userDto);
//	
//	// Perform the API call
//		ResponseEntity<UserDto> response = userController.getUserById(userId);
//	
//	// Verify the response
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(userDto, response.getBody());
//	}
//
//	@Test
//	public void testGetUserByEmail() {
//	// Prepare test data
//		String email = "test@example.com";
//		UserDto userDto = new UserDto(1, "testuser", "password", email, new Date(), true);
//	
//	// Mock dependencies' behavior
//		when(userService.getByEmailId(email)).thenReturn(userDto);
//	
//	// Perform the API call
//		ResponseEntity<UserDto> response = userController.getUserByEmail(email);
//	
//	// Verify the response
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(userDto, response.getBody());
//	}
//	
//}
//
