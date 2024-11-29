package com.shalom.sender_notification.service.sender.message_text;

import com.shalom.sender_notification.service.sender.ISenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("MessageTextService")
public class MessageTextService implements ISenderService {
    @Override
    public void send(Map<String, String> message) {
    }
}
