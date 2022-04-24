package com.arthurhan.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@NoArgsConstructor
@Configuration
public class NotificationQueueConfig
{
    @Value("${app-config.rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${app-config.rabbitmq.queues.notification}")
    private String notificationQueue;

    @Value("${app-config.rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange()
    {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue notificationQueue()
    {
        return new Queue(this.notificationQueue, true);
    }

    @Bean
    public Binding internalToNotificationBinding()
    {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }
}
