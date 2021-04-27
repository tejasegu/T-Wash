package com.twash.notificationservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twash.notificationservice.model.Notifications;
import com.twash.notificationservice.repository.NotificationDao;
import com.twash.notificationservice.repository.NotificationRepository;

@Service
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private NotificationRepository notificationrepo;
	@Override
	public String addNotifications(Notifications notification) {
		notification.setCreatedDateTime(LocalDate.now());
		notificationrepo.save(notification);
		return "Notification Added";
	}

	/*
	 * @Override public List<Notifications> getAllNotifications() {
	 * 
	 * return notificationrepo.findAll(); }
	 */

	/*
	 * @Override public Optional<Notifications> getNotificationbyId(String id) {
	 * 
	 * return notificationrepo.findById(id); }
	 */

	@Override
	public Optional<List<Notifications>> getNotificationbyUserId(String userId) {
		
		return notificationrepo.findByUserid(userId);
	}

	@Override
	public int getNotificationCountByUserId(String userid) {
		List<Notifications> notification= notificationrepo.findByUserid(userid).get();
		int count= notification.size();
		return count;
	}

}
