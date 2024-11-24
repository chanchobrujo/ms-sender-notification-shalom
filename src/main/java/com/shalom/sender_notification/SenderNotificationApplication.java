package com.shalom.sender_notification;

import com.shalom.sender_notification.service.listener.IListenerNotificationService;
import com.shalom.sender_notification.service.listener.ListenerNotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SenderNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenderNotificationApplication.class, args);
	}

}
