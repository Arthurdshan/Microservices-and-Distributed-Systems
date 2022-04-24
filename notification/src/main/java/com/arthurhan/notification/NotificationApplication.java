package com.arthurhan.notification;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication(
        scanBasePackages = {
                "com.arthurhan.notification",
                "com.arthurhan.amqp",
        }
)
public class NotificationApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
