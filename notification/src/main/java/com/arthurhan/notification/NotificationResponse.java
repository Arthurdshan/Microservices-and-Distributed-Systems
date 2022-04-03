package com.arthurhan.notification;

import com.arthurhan.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse
{
    private String toCustomerEmail;
    private String message;
    private String sender;
    private Instant sentAt;

    public static NotificationResponse of(Notification notification)
    {
        NotificationResponse notificationResponse = new NotificationResponse();
        BeanUtils.copyProperties(notification, notificationResponse);

        return notificationResponse;
    }
}
