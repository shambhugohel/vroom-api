package com.carzhub.shared.security;

import java.util.Optional;

public interface UserContext {

    Optional<CurrentUser> getCurrentUser();

}
