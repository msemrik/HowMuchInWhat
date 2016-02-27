package com.Controller;


import Business.Business;
import Business.Exceptions.CoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

@SpringBootApplication
@ComponentScan("com.Controller")
public class Application implements ServletContextInitializer {

    public static void main(String[] args) throws IOException, CoreException {
        SpringApplication.run(Application.class, args);
        Business.getBusiness();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }
}