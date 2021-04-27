package com.twash.bookingservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.twash.bookingservice.model.Image;

public interface ImageRepository extends MongoRepository<Image , String> {
       Optional<Image>  findByTitle(String title);
}
