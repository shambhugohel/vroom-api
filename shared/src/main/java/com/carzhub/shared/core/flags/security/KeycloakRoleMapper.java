package com.carzhub.shared.core.flags.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Keycloak → internal application roles
 */
public final class KeycloakRoleMapper {

  private KeycloakRoleMapper() {
  }

  public static Set<Role> map(Set<String> kcRoles) {
    return kcRoles.stream()
        .map(r -> {
          try {
            return Role.valueOf(r.toUpperCase());
          } catch (IllegalArgumentException e) {
            return null; // ignore unknown KC role
          }
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }
}
