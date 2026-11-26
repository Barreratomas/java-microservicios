package com.vaxi.spring_boot_microservice_3_api_gateway.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vaxi.spring_boot_microservice_3_api_gateway.model.User;
import com.vaxi.spring_boot_microservice_3_api_gateway.service.UserService;
import com.vaxi.spring_boot_microservice_3_api_gateway.utils.SecurityUtils;

import java.util.Set;

public class CustomUserDetailsService implements UserDetailsService{

    private final UserService userService;
    
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado:"+username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.converToAuthority(user.getRole().name()));
        return UserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .username(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

}
