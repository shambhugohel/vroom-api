package com.carzhub.shared.core.flags.security;

import java.util.Map;
import java.util.Set;

public final class RoleHierarchy {

  private RoleHierarchy() {
  }

  private static final Map<Role, Set<Role>> INHERITS = Map.of(
      Role.PREMIUM_USER, Set.of(Role.USER),
      Role.MODERATOR, Set.of(Role.USER),
      Role.ADMIN, Set.of(Role.MODERATOR),
      Role.SUPER_ADMIN, Set.of(Role.ADMIN));

  public static boolean has(Role current, Role required) {
    if (current == required) {
      return true;
    }

    Set<Role> parents = INHERITS.getOrDefault(current, Set.of());
    return parents.stream().anyMatch(r -> has(r, required));
  }
}
