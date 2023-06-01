/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ah.baseprojectjsf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author AHERNANDEZ
 */
public class JSONTable {
    
     /**
     * Convierte una respuesta https a un String
     *
     * @param is ImputStream response
     * @return String of response
     */
      public  StringBuilder inputStreamToString(InputStream is) {
        String line = "";
        StringBuilder stringbuilder = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                stringbuilder.append(new String(line.getBytes(StandardCharsets.UTF_8)));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return stringbuilder;
    }
    
}
