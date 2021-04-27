package com.twash.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.twash.userservice.model.Reviews;



public interface ReviewsRepository  extends MongoRepository<Reviews, Long> {
	Optional<List<Reviews>> findByWasherid(String washerid);
}
