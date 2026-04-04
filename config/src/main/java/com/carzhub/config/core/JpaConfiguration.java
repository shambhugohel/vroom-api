package com.carzhub.config.core;

import com.carzhub.shared.constants.ApplicationConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing(auditorAwareRef = ApplicationConstants.AUDIT_PROVIDER_NAME)
@EnableJpaRepositories(basePackages = ApplicationConstants.PKG_REPOSITORY)
public class JpaConfiguration {

}
