package com.twash.bookingservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.twash.bookingservice.model.Bookings;


public interface BookingsRepository extends MongoRepository<Bookings, Long> {
	Optional<List<Bookings>> findByUserid(String userid);
	Optional<List<Bookings>> findByWasherid(String washerid);
}
