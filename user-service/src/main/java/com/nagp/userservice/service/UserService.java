package com.nagp.userservice.service;

import com.nagp.userservice.dto.UserDTO;
import com.nagp.userservice.entity.User;

/**
 * Service interface to get and create user details of the user. The interface
 * provides two methods to get and create user respectively.
 * 
 * @author santoshkumar02
 * @see UserServiceImpl
 */
public interface UserService {
	/**
	 * This method returns a user details based on the id
	 * 
	 * @return User
	 */
	UserDTO getUserById(Long id);

	/**
	 * This method creates a new user
	 * 
	 * @return User
	 */
	User createUser(UserDTO user);
}
