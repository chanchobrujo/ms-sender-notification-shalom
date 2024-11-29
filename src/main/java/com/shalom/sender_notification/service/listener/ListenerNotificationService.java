package com.shalom.sender_notification.service.listener;

import com.shalom.sender_notification.properties.RedisProperties;
import com.shalom.sender_notification.service.sender._message.MesageService;
import io.lettuce.core.Consumer;
import io.lettuce.core.RedisBusyException;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.api.sync.RedisCommands;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static io.lettuce.core.XReadArgs.StreamOffset.from;
import static io.lettuce.core.XReadArgs.StreamOffset.lastConsumed;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListenerNotificationService implements IListenerNotificationService {
    private final MesageService mesageService;
    private final RedisProperties redisProperties;
    private final RedisCommands<String, String> lettuceRedisConnection;

    private String getKey() {
        return this.redisProperties.getKey();
    }

    private String getGroup() {
        return this.redisProperties.getGroup();
    }

    private void createGroup() {
        try {
            this.lettuceRedisConnection.xgroupCreate(from(this.getKey(), "0-0"), this.getGroup());
        } catch (RedisBusyException redisBusyException) {
            log.error(redisBusyException.getMessage());
        }
    }

    private void listenerNotification() {
        try {
            Consumer<String> consumer = Consumer.from(this.getGroup(), "consumer_1");
            XReadArgs.StreamOffset<String> sds = lastConsumed(this.getKey());
            this.lettuceRedisConnection.xreadgroup(consumer, sds)
                    .stream()
                    .map(this.mesageService::send)
                    .filter(StringUtils::isNotEmpty)
                    .forEach(id -> this.lettuceRedisConnection.xack(this.getKey(), this.getGroup(), id));
        } catch (Exception e) {
            log.error(" ".concat("Error al procesar notificaciones."));
            log.error(" ".concat(e.getMessage()));
        }
    }

    @Override
    public void run(String... args) {
        this.createGroup();
        while (true) {
            this.listenerNotification();
        }
    }
}
