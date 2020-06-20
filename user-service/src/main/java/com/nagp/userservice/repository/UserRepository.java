package com.nagp.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.nagp.userservice.entity.User;

/**
 * JPA crud repository interface for user entity
 * @author santoshkumar02
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
