package com.carzhub.config.core;

import com.carzhub.shared.security.CurrentUser;
import com.carzhub.shared.security.UserContext;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(name = "app.security.enabled", havingValue = "true", matchIfMissing = true)
public class SpringSecurityUserContext implements UserContext {

    @Override
    public Optional<CurrentUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtToken) {
            Jwt jwt = jwtToken.getToken();
            String id = jwt.getClaimAsString("sub"); // Keycloak Subject ID
            String username = jwt.getClaimAsString("preferred_username");
            String email = jwt.getClaimAsString("email");

            Set<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());

            return Optional.of(CurrentUser.builder()
                    .id(id)
                    .username(username)
                    .email(email)
                    .roles(roles)
                    .build());
        }

        return Optional.empty();
    }
}
