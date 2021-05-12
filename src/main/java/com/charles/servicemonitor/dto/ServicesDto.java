package com.charles.servicemonitor.dto;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicesDto {
    
    private String service;
    private String status;
    private Boolean notified;

}
