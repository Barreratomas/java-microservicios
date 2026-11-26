package com.vaxi.spring_boot_microservice_3_api_gateway.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUtils {

    public static final String ROLE_PREFIX = "ROLE_";

    private SecurityUtils() {
    }

    public static SimpleGrantedAuthority converToAuthority(String role){
        
        String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
          
        return new SimpleGrantedAuthority(formattedRole);
    }

}
