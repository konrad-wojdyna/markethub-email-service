package com.markethub.emailservice.service;


import com.markethub.emailservice.model.ListingCreatedEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendListingCreatedEmail(ListingCreatedEmailRequest request){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.userEmail());
        message.setSubject("Twoje ogłoszenie zostało opublikowane: " + request.title());
        message.setText("Cześć " + request.userFirstName() + ", Twoje ogłoszenie " +
                request.title() + " zostało opublikowane.");

         mailSender.send(message);
    }

}
