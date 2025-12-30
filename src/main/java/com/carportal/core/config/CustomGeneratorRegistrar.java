package com.carportal.core.config;

import java.util.HashMap;
import java.util.Map;

public class CustomGeneratorRegistrar {

  public Map<String, Class<?>> getRegisteredGenerators() {
    Map<String, Class<?>> map = new HashMap<>();
    map.put("prefixed-sequence", PrefixedSequenceIdGenerator.class);
    return map;
  }
}
