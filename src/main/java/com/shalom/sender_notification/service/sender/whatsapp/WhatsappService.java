package com.shalom.sender_notification.service.sender.whatsapp;

import com.shalom.sender_notification.service.sender.ISenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("WhatsappService")
public class WhatsappService implements ISenderService {
    @Override
    public void send(Map<String, String> message) {

    }
}
