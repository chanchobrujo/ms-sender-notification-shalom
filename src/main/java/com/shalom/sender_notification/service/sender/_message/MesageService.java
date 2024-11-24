package com.shalom.sender_notification.service.sender._message;

import com.shalom.sender_notification.service.sender.ISenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class MesageService {

    @Autowired
    @Qualifier(value = "MailService")
    private ISenderService mailService;

    @Autowired
    @Qualifier(value = "MessageTextService")
    private ISenderService messageTextService;

    @Autowired
    @Qualifier(value = "WhatsappService")
    private ISenderService whatsappService;

    public void send(Map<String, String> message) {
        this.mailService.send(message);
        this.whatsappService.send(message);
        this.messageTextService.send(message);
    }
}
