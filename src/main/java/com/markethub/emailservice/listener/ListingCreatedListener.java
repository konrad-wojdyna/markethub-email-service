package com.markethub.emailservice.listener;


import com.markethub.emailservice.config.RabbitMQConfig;
import com.markethub.emailservice.model.ListingCreatedEmailRequest;
import com.markethub.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListingCreatedListener {

    private final EmailService emailService;

    @RabbitListener(queues = RabbitMQConfig.LISTING_CREATED_QUEUE)
    public void listen(ListingCreatedEmailRequest request)
    {
        emailService.sendListingCreatedEmail(request);
    }
}
