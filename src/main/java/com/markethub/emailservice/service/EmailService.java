package com.markethub.emailservice.service;


import com.markethub.emailservice.model.ListingCreatedEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private RabbitTemplate rabbitTemplate;

    public void sendListingCreatedEmail(ListingCreatedEmailRequest request){

    }

}
