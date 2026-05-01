package com.markethub.emailservice.listener;


import com.markethub.emailservice.config.RabbitMQConfig;
import com.markethub.emailservice.model.ListingCreatedEmailRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ListingCreatedListener {

    @RabbitListener(queues = RabbitMQConfig.LISTING_CREATED_QUEUE)
    public void listen(ListingCreatedEmailRequest request)
    {
        System.out.println("Received message: " + request.title());
    }
}
