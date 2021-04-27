package com.twash.userservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.twash.userservice.model.Users;

/**
 * UsersRepository Interface Extends MongoRepository Interface For CRUD Operations 
 *
 */
public interface UsersRepository extends MongoRepository<Users, Long>{
	
	Optional<Users> findByEmail(String email);
	Optional<List<Users>> findByRole(String role);
	Predicate<? super List<Users>> findByArea(String area);
	
}
