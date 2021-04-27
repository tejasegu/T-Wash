package com.twash.notificationservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twash.notificationservice.config.RabbitMqConfig;
import com.twash.notificationservice.model.Notifications;

@Component
public class RabbitMqConsumer {
	
	@Autowired
	private NotificationDaoImpl notificationdao;

	 @RabbitListener(queues = RabbitMqConfig.QUEUE)
	    public void consumeMessageFromQueue(Notifications notification) {

				notificationdao.addNotifications(notification);
	    }
}
