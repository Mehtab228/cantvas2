package com.cantvas2.cantvas2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cantvas2.cantvas2.models.*;

@Configuration
public class RegistrationConfig {

    @Bean
    ConcreteUserFactory userFactory() {
        return new ConcreteUserFactory();
    }
}
