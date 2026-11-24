package com.vaxi.spring_boot_microservice_2_compra.security;

import java.util.List;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityUsersProperties.class)
public class SecurityConfig {

    private static final String ACCESS_DENIED_URL = "http://localhost:4200/";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/compra").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/compra/**").hasAnyRole("ADMIN", "DEV")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.sendRedirect(ACCESS_DENIED_URL)));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(SecurityUsersProperties properties,
                                                 PasswordEncoder passwordEncoder) {
        List<UserDetails> users = properties.users().stream()
                .map(user -> User.withUsername(user.username())
                        .password(passwordEncoder.encode(user.password()))
                        .roles(user.role())
                        .build())
                .toList();

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
