package com.charles.servicemonitor.service.email;

import com.charles.servicemonitor.entity.Contact;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    
    void sendMessage(Contact contact);
}
