package com.twash.notificationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twash.notificationservice.model.Notifications;
import com.twash.notificationservice.service.NotificationDaoImpl;

import io.swagger.v3.oas.annotations.Operation;




@RestController
public class NotificationController {

	@Autowired
	private NotificationDaoImpl notificationdao;
	
	        //To Insert Notification Into DataBase
			@Operation(summary = "Adds a new notification")
			@PostMapping("/notification")
			public ResponseEntity<String> createNotification(  @RequestBody Notifications notification) {
				try {
					notificationdao.addNotifications(notification);
					return ResponseEntity.ok("Review added suceesfully with Id:"+notification.getId());
					}
					catch (Exception e) {
						 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
					}
			}
			
			
	       //To Get All the Notifications from userid
			@Operation(summary = "Get a  notification from database using id")
			@GetMapping("/notification/find/{userid}")
			public ResponseEntity<?> findNotificationById (@PathVariable("userid") String userid) {
				try {
					List<Notifications> notify=notificationdao.getNotificationbyUserId(userid).get();
					
					return  ResponseEntity.ok(notify);
				}
				catch(Exception e) {
					return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
			
			 //To Get All the count of Notifications for specific userid
			@Operation(summary = "Get the count of notifications from database for specific userid")
			@GetMapping("/notification/count/{userid}")
			public ResponseEntity<?> getCount(@PathVariable("userid") String userid){
				try {
					int count=notificationdao.getNotificationCountByUserId(userid);
					return  ResponseEntity.ok(count);
			}
				catch(Exception e) {
					return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
	
}
}
