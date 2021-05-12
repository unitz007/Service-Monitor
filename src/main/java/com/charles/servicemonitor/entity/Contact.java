package com.charles.servicemonitor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(Include.NON_NULL)
public class Contact {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    
    private String name;
    private String email;

    @OneToOne
    @JsonIgnore
    private Unit unit;
    
}
