package com.cantvas2.cantvas2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;

import com.cantvas2.cantvas2.models.*;

@Configuration
public class SignupConfig {

    @Bean
    @Scope("singleton")
    ConcreteUserFactory userFactory() {
        return new ConcreteUserFactory();
    }

    @Bean
    @SessionScope
    SignupForm signupForm() {
        return new SignupForm();
    }
}
