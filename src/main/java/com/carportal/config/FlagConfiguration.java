package com.carportal.config;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.flags")
public class FlagConfiguration {

  private Map<String, Boolean> features = new HashMap<>();

  public boolean isEnabled(String featureName) {
    return features.getOrDefault(featureName, false);
  }
}
