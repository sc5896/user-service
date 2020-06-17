package com.nagp.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.nagp.userservice.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {

}
