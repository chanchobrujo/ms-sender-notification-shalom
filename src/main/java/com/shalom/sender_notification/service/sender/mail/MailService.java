package com.shalom.sender_notification.service.sender.mail;

import com.shalom.sender_notification.model.exception.BusinessException;
import com.shalom.sender_notification.service.sender.ISenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Optional.ofNullable;

@Slf4j
@Component("MailService")
public class MailService implements ISenderService {
    @Autowired
    private JavaMailSender sender;

    @Override
    public void send(Map<String, String> messageBody) {
        if (!messageBody.isEmpty()) {
            var text = ofNullable(messageBody.get("text"))
                    .orElseThrow(() -> new BusinessException("Notificacion invalida."));
            var email = ofNullable(messageBody.get("email"))
                    .orElseThrow(() -> new BusinessException("Notificacion invalida."));
            var subject = ofNullable(messageBody.get("subject"))
                    .orElseThrow(() -> new BusinessException("Notificacion invalida."));

            try {
                MimeMessage message = this.sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                helper.setTo(email);
                helper.setText(text, false);
                helper.setSubject(subject);
                this.sender.send(message);
            } catch (MessagingException e) {
                log.error(e.getMessage());
            } catch (Exception e) {
                log.error("ERROR AL ENVIAR MENSAJE DE TEXTO");
                log.error(e.getMessage());
                log.error(messageBody.toString());
            }
        }
    }
}
