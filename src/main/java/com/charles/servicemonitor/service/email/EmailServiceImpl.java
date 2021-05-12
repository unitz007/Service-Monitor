package com.charles.servicemonitor.service.email;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.charles.servicemonitor.entity.Contact;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("springEmailServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Value("${mail.sender}")
    private String from;

    @Value("${mail.subject}")
    private String subject;

    @Override
    public void sendMessage(Contact contact) {
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(subject);
        message.setTo(contact.getEmail());
        
    
        try {
            message.setText(String.format("%s on %s is down", contact.getUnit().getName(), InetAddress.getLocalHost().getHostAddress()));
        } catch (UnknownHostException e) {
           log.error(e.getMessage());
        }
     
        emailSender.send(message);
        
    }


    
}
