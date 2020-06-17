package com.nagp.userservice.service;

import com.nagp.userservice.dto.UserDTO;
import com.nagp.userservice.entity.User;

public interface UserService {
	/**
	 * This method returns a user based on the id
	 * @return User
	 */
	UserDTO getUserById(Long id);

	/**
	 * This method creates a new user
	 * @return User
	 */
	User createUser(UserDTO user);
}
