package com.arthurhan.notification;

import com.arthurhan.clients.notification.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotificationService
{
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository)
    {
        this.notificationRepository = notificationRepository;
    }

    public void send(NotificationRequest notificationRequest)
    {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.getToCustomerId())
                        .toCustomerEmail(notificationRequest.getToCustomerEmail())
                        .sender("ADMIN")
                        .message(notificationRequest.getMessage())
                        .sentAt(Instant.now())
                        .build()
        );
    }
}
