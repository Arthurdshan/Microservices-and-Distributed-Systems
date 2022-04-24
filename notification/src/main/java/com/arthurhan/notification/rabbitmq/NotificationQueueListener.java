package com.arthurhan.notification.rabbitmq;

import com.arthurhan.clients.notification.NotificationRequest;
import com.arthurhan.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationQueueListener
{
    private final NotificationService notificationService;

    @RabbitListener(queues = "${app-config.rabbitmq.queues.notification}")
    public void consumeNotificationMessage(NotificationRequest notificationRequest)
    {
        log.info("Received {} from queue.", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
