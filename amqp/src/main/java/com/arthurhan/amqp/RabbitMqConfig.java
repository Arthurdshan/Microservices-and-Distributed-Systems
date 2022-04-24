package com.arthurhan.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMqConfig
{
    private final ConnectionFactory connectionFactory;

    // send messages to the queue
    @Bean
    public AmqpTemplate amqpTemplate()
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());

        return rabbitTemplate;
    }

    // receive messages from the queue
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory()
    {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();

        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(jacksonConverter());

        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public MessageConverter jacksonConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
}
