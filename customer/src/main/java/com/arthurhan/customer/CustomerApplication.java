package com.arthurhan.customer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(
        basePackages = "com.arthurhan.clients"
)
@EnableEurekaClient
@EnableRabbit
@SpringBootApplication(
    scanBasePackages = {
        "com.arthurhan.customer",
        "com.arthurhan.amqp",
    }
)
public class CustomerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
