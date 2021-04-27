package com.twash.notificationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.twash.notificationservice.model.Packages;

public interface PackageRepository extends MongoRepository<Packages, String> {

}
