package com.nagp.userservice.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.userservice.dto.UserDTO;
import com.nagp.userservice.entity.User;
import com.nagp.userservice.repository.UserRepository;
import com.nagp.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			logger.error("No user found with id:"+id);
			throw new RuntimeException("No user found with id:"+id);
		}
		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(user.get(), userDto);
		return userDto;
	}

	@Transactional
	@Override
	public User createUser(UserDTO user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		userRepository.save(userEntity);
		return userEntity;
	}

}
