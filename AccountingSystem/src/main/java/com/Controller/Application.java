package com.Controller;


import com.DAO.DBAccess;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@ComponentScan("com.Controller")
public class Application implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Session session = DBAccess.getSession();
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