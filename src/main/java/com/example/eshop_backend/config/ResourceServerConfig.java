package com.example.eshop_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        return http
                .httpBasic()
                .disable()
                .formLogin()
                .disable()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**")
                .permitAll()
                .requestMatchers("/admin/**", "/endUser/**","/provider/**")
                .authenticated()
                .and()
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(converter())// after get jwt set a converter to get Roles
                .and()
                .and()
                .build();

    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    private JwtAuthenticationConverter converter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter((j) -> {

            Map<String, Object> realmAccess = (Map<String, Object>) j.getClaims().get("resource_access");
            Map<String, Object> eshopAccess=(Map<String, Object>) realmAccess.get("eshop_backend");
            if (realmAccess == null || realmAccess.isEmpty()) {
                return new ArrayList<>();
            }

            Collection<GrantedAuthority> returnValue = ((List<String>) eshopAccess.get("roles"))
                    .stream().map(roleName -> "ROLE_" + roleName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            System.out.println(returnValue);
            return returnValue;


        });
        return jwtAuthenticationConverter;
    }

}
