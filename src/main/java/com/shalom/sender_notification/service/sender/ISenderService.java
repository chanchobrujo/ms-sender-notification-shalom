package com.shalom.sender_notification.service.sender;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface ISenderService {
    void send(Map<String, String> message) throws MessagingException;
}
