package com.Controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {

    private String allowOrigin;
    private String[] allowedOrigins = new String[]{"localhost:8080", "192.168.0.254:8080"};

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        setDomain(request);
        response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, accept, authorization, Content-Type, X-XSRF-TOKEN");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }


    public void setDomain(HttpServletRequest request) {
        String originDomain = request.getHeader("host");
        allowOrigin = "http://localhost:8080";

        for (String url : allowedOrigins) {
            if (url.equals(originDomain)) {
                allowOrigin = "http://" + originDomain;
                break;
            }

        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
