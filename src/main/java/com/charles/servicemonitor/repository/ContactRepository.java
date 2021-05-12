package com.charles.servicemonitor.repository;

import com.charles.servicemonitor.entity.Contact;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    
}
