package com.markethub.emailservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String LISTING_CREATED_QUEUE = "listing.created";
    public static final String LISTING_CREATED_DLQ = "listing.created.dlq";
    public static final String LISTING_CREATED_DLE = "listing.created.dle";
        private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    @Bean
    public Queue listingCreatedQueue() {

        return QueueBuilder.durable(LISTING_CREATED_QUEUE)
                .withArgument(X_DEAD_LETTER_EXCHANGE, LISTING_CREATED_DLE).build();
    }

    @Bean
    public Queue listingCreatedDlq() {
        return QueueBuilder.durable(LISTING_CREATED_DLQ).build();
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(LISTING_CREATED_DLE);
    }

    @Bean
    public Binding dlqBinding(){
        return BindingBuilder
                .bind(listingCreatedDlq())
                .to(deadLetterExchange())
                .with(LISTING_CREATED_QUEUE);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
