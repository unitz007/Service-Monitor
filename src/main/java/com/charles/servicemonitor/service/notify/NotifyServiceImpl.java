package com.charles.servicemonitor.service.notify;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.charles.servicemonitor.entity.Contact;
import com.charles.servicemonitor.entity.Unit;
import com.charles.servicemonitor.service.email.EmailService;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("notifyServiceImpl")
@Slf4j
@RequiredArgsConstructor
public class NotifyServiceImpl implements NotifyService {


    @Autowired
    @Qualifier("springEmailServiceImpl")
    private EmailService emailService;

    @Override
    public void notify(Unit unit) {

       
        unit.getContacts().forEach(contact -> {
            
            emailService.sendMessage(contact);
         

            log.info(String.format("notification for %s sent to %s on %s", unit.getName(), contact.getName(), contact.getEmail()));
        });
        
    }


}
