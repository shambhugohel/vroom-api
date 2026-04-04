package com.carzhub.config.core;

import com.carzhub.shared.security.CurrentUser;
import com.carzhub.shared.security.UserContext;
import java.util.Optional;
import java.util.Set;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.security.enabled", havingValue = "false")
public class DevUserContext implements UserContext {

    @Override
    public Optional<CurrentUser> getCurrentUser() {
        return Optional.of(CurrentUser.builder()
                .id("dev-user-id")
                .username("dev_admin")
                .email("admin@dev.local")
                .roles(Set.of("ROLE_ADMIN", "ROLE_USER"))
                .build());
    }
}
