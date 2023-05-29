/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ah.baseprojectjsf.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AHERNANDEZ
 */
public class FiltroNavegacion implements Filter  {
    
     FilterConfig fc;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         fc = filterConfig;
    }
    
    
     @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
          HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        resp.setDateHeader("Expires", 0); // Proxies.
        HttpSession session = req.getSession(true);
        String pageRequested = req.getRequestURL().toString();
         System.out.println("entrando a filtro de navegacion");
        try {
        
        
        chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println("error en filtro de navegacion [" + e.getMessage() + "]");
        }
        
        
    }
    
    
    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
