package com.cantvas2.cantvas2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

import com.cantvas2.cantvas2.config.*;

import java.util.List;

import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;

import com.cantvas2.cantvas2.models.*;

@Configuration
@EnableWebSecurity
@Profile(value = { "test", "development", "production" })
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(withDefaults())
        .cors(withDefaults())
        .addFilterBefore(new UserTypeAuthenticationFilter(
            new ProviderManager(List.of(authenticationProvider()))),
            UserTypeAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> {
          auth.mvcMatchers("/", "/login").permitAll()
              .mvcMatchers("/courses/**").authenticated();
        }).formLogin(form -> {
          form.loginPage("/login").successForwardUrl("/courses");
        }).oauth2Login(oauth -> {
          oauth.loginPage("/login").defaultSuccessUrl("/courses");
        }).logout(logout -> logout
            .clearAuthentication(true)
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login"))
        .build();
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(toH2Console());
  }

  @Bean
  @Profile(value = { "test", "development" })
  JdbcUserDetailsManager jdbcUserDetailsService() {
    JdbcUserDetailsManager jdbc = new StringlyTypedJdbcUserDetailsManager(
        new DriverManagerDataSource("jdbc:h2:mem:cantvas", "sa", ""));
    UserDetails user = CantvasUser.builder()
        .username("ben")
        .password("foobar")
        .userType("STUDENT")
        // .passwordEncoder(password -> passwordEncoder().encode(password))
        // .authorities("USER")
        .build();
    UserDetails admin = CantvasUser.builder()
        .username("david")
        .password("bazquux")
        .userType("TEACHER")
        // .passwordEncoder(password -> passwordEncoder().encode(password))
        // .authorities("TEACHER, ADMIN")
        .build();
    jdbc.createUser(user);
    jdbc.createUser(admin);
    return jdbc;
  }

  @Bean
  @Profile("test")
  H2ConsoleProperties h2ConsoleProperties() {
    return new H2ConsoleProperties();
  }

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(jdbcUserDetailsService());
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

}
