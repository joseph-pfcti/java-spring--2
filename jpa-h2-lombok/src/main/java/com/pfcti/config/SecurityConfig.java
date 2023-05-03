package com.pfcti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.pfcti.config.CustomJwtAuthenticationConverter;

@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        manager.createUser(User.withUsername("user")
//                .password("{noop}userPass")
//                .roles("USER")
//                .build());
//
//        manager.createUser(User.withUsername("admin")
//                .password("{noop}adminPass")
//                .roles("USER", "ADMIN")
//                .build());
//
//        return manager;
//    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE)
                .hasRole("admin")
                .requestMatchers("/v1/api/client/**", "/v1/api/employees")
                .hasAnyRole("user", "admin")
                .requestMatchers("/v1/api/login")
                .anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
        httpSecurity.oauth2Login();
        return httpSecurity.build();
    }
}
