package com.carzhub.shared.security;

import java.util.Set;
import lombok.Builder;

@Builder
public record CurrentUser(String id, String username, String email, Set<String> roles) {
}
