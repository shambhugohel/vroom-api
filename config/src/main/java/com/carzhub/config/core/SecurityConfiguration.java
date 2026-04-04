package com.carzhub.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(name = "app.security.enabled", havingValue = "true", matchIfMissing = true)
public class SecurityConfiguration {

        private static final String[] PUBLIC_URLS = {
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-ui/index.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/actuator/**"
        };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(PUBLIC_URLS).permitAll()
                                                .anyRequest().authenticated())
                                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
                                        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
                                        jwtAuthenticationConverter
                                                        .setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
                                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter);
                                }));

                return http.build();
        }
}
