package com.charles.servicemonitor.service.notify;

import com.charles.servicemonitor.entity.Contact;
import com.charles.servicemonitor.entity.Unit;

import org.springframework.stereotype.Service;

@Service
public interface NotifyService {

    void notify(Unit unit);
    
}
