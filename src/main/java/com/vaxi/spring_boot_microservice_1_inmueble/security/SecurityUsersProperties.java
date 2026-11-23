package com.vaxi.spring_boot_microservice_1_inmueble.security;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security")
public record SecurityUsersProperties(List<User> users) {

    public record User(String username, String password, String role) {
    }
}
