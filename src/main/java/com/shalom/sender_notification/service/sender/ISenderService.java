package com.shalom.sender_notification.service.sender;

import java.util.Map;

public interface ISenderService {
    void send(Map<String, String> message);
}
