package com.charles.servicemonitor.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.JoinColumn;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "SERVICE_ID")
    private Long id;

    @Column(name = "SERVICE_NAME", nullable = false, unique = true)
    private String name;


    @Column(name = "SERVICE_PID")
    @JsonProperty(access = Access.READ_ONLY)
    private String pid;

    @Column(name = "SERVICE_STATUS", nullable = false)
    @JsonProperty(access = Access.READ_ONLY)
    private String status;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Contact> contacts;
    
}
