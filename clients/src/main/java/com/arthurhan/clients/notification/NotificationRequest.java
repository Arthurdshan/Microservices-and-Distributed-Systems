package com.arthurhan.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest
{
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String message;
}
