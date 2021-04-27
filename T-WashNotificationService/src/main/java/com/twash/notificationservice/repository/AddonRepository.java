package com.twash.notificationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.twash.notificationservice.model.Addon;

public interface AddonRepository extends MongoRepository<Addon, String> {

}
