package com.example.tasksmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/executors/**").hasRole("ADMIN")
                                .requestMatchers("/tasks/all").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/tasks/add").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/tasks/status/*").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/tasks/*/add-executor").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/tasks/*/assign-executor").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/tasks/*/update-status").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/tasks/*/delete").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}