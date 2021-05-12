package com.charles.servicemonitor.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.charles.servicemonitor.dto.ServicesDto;
import com.charles.servicemonitor.service.notify.NotifyService;
import com.charles.servicemonitor.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemdFactory {
    
   
    private List<ServicesDto> servicesDtos = new ArrayList<>();
    private final UnitService unitService;

    @Autowired
    @Qualifier("notifyServiceImpl")
    private NotifyService notifyService;

    public List<ServicesDto> getServices() throws IOException {

        return servicesDtos;
    }

    @Scheduled(fixedRate = 5000)
    public void checkAndUpdateServices() throws UnknownHostException {

        
        unitService.getAll().forEach(unit -> {
            String curStatus = Utils.systemdCmd(String.format("systemctl is-active %s", unit.getName()));
            if (!curStatus.equals(unit.getStatus())) {
                log.info(String.format("%s status changed from %s to %s", unit.getName(), unit.getStatus(), curStatus));
                if (curStatus.equals("inactive") || curStatus.equals("failed")) {
                    
                    try {
                        log.info(String.format("%s on %s is down", unit.getName(),
                                InetAddress.getLocalHost().getHostAddress()));
                      
                            // notify contacts
                            notifyService.notify(unit); 

                    } catch (UnknownHostException e) {
                        log.error(e.getMessage());
                    }
                }
                unit.setStatus(curStatus);
                unitService.saveUnit(unit);
            }
        });
                    
            
    }

    // private boolean exists(String service) {
    //    return servicesDtos.stream().filter(s -> s.getService().equals(service))
    //             .findAny().isPresent();
    // }

  
    // private void notify(ServicesDto servicesDto) {
    //     System.out.println(String.format("%s is down", servicesDto.getService()));

    // }


}
