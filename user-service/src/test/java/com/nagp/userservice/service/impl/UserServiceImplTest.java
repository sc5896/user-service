package com.nagp.userservice.service.impl;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.nagp.userservice.dto.UserDTO;
import com.nagp.userservice.entity.User;
import com.nagp.userservice.repository.UserRepository;

public class UserServiceImplTest {
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private Logger logger;

	@Mock
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setId(1L);
		user.setName("John");
		user.setAge(23);
		user.setEmail("john.doe@google.com");
	}

	@DisplayName("Test UserService getUserById with user id 1")
	@Test
	public void getUserById() {
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(user));
		UserDTO user = userService.getUserById(1L);
		Assertions.assertNotNull(user);
		Assertions.assertEquals("John", user.getName());
		Assertions.assertEquals(23, user.getAge());
		Assertions.assertEquals("john.doe@google.com", user.getEmail());
	}

	@DisplayName("Test UserService getUserById with user id 3 and expecting excetion")
	@Test
	public void getUserByIdAssertThrowsException() {
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		Assertions.assertThrows(RuntimeException.class, () -> {
			userService.getUserById(3L);
		});
	}
}
