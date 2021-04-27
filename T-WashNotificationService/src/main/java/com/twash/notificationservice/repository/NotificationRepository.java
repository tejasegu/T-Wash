package com.twash.notificationservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.twash.notificationservice.model.Notifications;

public interface NotificationRepository extends MongoRepository<Notifications, String> {
         Optional<List<Notifications>> findByUserid(String userid);
}
