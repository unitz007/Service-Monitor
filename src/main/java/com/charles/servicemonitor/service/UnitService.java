package com.charles.servicemonitor.service;

import java.util.List;

import com.charles.servicemonitor.entity.Unit;
import com.charles.servicemonitor.repository.ContactRepository;
import com.charles.servicemonitor.repository.UnitRepository;
import com.charles.servicemonitor.utils.Utils;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnitService {
    
    private final UnitRepository unitRepository;
    private final ContactRepository contactRepository;

    public Unit saveUnit(Unit unit) {
       
        unit.setPid(getProcessId(unit.getName()));
        unit.setStatus(getServiceStatus(unit.getName()));
        
        Unit savedUnit = unitRepository.save(unit);
        unit.getContacts().forEach(c -> {
            c.setUnit(savedUnit);
            contactRepository.save(c);
        });

        return unit;

    }

    public Iterable<Unit> getAll() {
        return unitRepository.findAll();
    }

    public Unit getUnitByName(String name) {
       return unitRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException(String.format("%s does not exist", name)));
    }

    // public List<Unit> getByOwner(String owner) {
    //     return unitRepository.findByOwner(owner);
    // }

    // private helper methods

    private String getProcessId(String serviceName) {
        return Utils
                .systemdCmd(String.format("systemctl show --property MainPID --value %s", serviceName));
    }

    private String getServiceStatus(String service) {
        String status = Utils.systemdCmd(String.format("systemctl is-active %s", service));

        // if (status.equals("inactive")) 
        //     throw new RuntimeException("Invalid service");
       
        return status;
        
    }
    




}
