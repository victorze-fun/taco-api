package com.victorze.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.victorze.tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}