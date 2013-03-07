package org.example;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RoutingTimeFilter implements Filter{

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void destroy() {
    }
    
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequestWrapper httpReq =
                new HttpServletRequestWrapper((HttpServletRequest) req);
        
        if(httpReq.getHeader("x-request-start") != null) {        
            Date date = new Date();
            long now = date.getTime();
            long routeTime = now - Long.valueOf(httpReq.getHeader("x-request-start"));
            if(routeTime < 0) routeTime = 0;
            System.out.println("at=metric queue=" + routeTime + "ms");
        }
        chain.doFilter(req, res);
    }
    
}
