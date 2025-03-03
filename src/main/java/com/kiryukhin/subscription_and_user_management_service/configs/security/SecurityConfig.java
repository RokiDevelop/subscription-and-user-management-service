package com.kiryukhin.subscription_and_user_management_service.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/users/*").permitAll()
                        .requestMatchers("/swagger-ui/*", "/v3/api-docs", "/v3/api-docs/*").permitAll()
                        .anyRequest().permitAll());

        SecurityFilterChain chain = httpSecurity.build();
        return chain;
    }
}
