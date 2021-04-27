package com.twash.notificationservice.repository;

import java.util.List;
import java.util.Optional;

import com.twash.notificationservice.model.Notifications;


public interface NotificationDao {
	
	public String addNotifications(Notifications notification);
	//public List<Notifications> getAllNotifications();
	//public Optional<Notifications> getNotificationbyId(String id);
	public Optional<List<Notifications>> getNotificationbyUserId(String userId);
    public int getNotificationCountByUserId (String userid);
	
}
