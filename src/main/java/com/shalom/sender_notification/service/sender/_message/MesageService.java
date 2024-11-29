package com.shalom.sender_notification.service.sender._message;

import com.shalom.sender_notification.service.sender.ISenderService;
import io.lettuce.core.StreamMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public String send(StreamMessage<String, String> streamMessage) {
        var id = streamMessage.getId();
        var message = streamMessage.getBody();

        try {
            this.mailService.send(message);
            this.whatsappService.send(message);
            this.messageTextService.send(message);
            return id;
        } catch (Exception e) {
            log.error(" ".concat("Error al procesar el mensaje{"+id+"}: ").concat(message.toString()));
            log.error(" ".concat(e.getMessage()));
        }
        return null;
    }
}
