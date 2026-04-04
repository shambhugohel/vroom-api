package com.carzhub.shared.core.config;

import com.carzhub.shared.constants.ApplicationConstants;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component(ApplicationConstants.AUDIT_PROVIDER_NAME)
public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    // TODO: Integrate with Spring Security to fetch the actual user
    return Optional.of("system");
  }
}
