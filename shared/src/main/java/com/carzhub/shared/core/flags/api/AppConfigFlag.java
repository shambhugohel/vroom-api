package com.carzhub.shared.core.flags.api;

public enum AppConfigFlag implements Flag {

  @FlagGroup("System")
  ENABLE_LOGGING,

  @FlagGroup("System")
  CACHE_ENABLED,

  @FlagGroup("Features")
  NEW_UI_ENABLED,

  @FlagGroup("Experiments")
  EXPERIMENTAL_FEATURE_X
}
