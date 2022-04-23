package com.arthurhan.notification;

import com.arthurhan.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class NotificationService
{
    private final NotificationRepository notificationRepository;

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
