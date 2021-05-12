package com.charles.servicemonitor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
    
    public static String systemdCmd(String cmd) {
        

        try {
            Process p = Runtime.getRuntime().exec(cmd);

            String response = new BufferedReader(new InputStreamReader(p.getInputStream())).readLine();

            return response;
        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }


    }


}
