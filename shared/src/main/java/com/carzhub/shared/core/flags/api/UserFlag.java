package com.carzhub.shared.core.flags.api;

public enum UserFlag implements Flag {

  @FlagGroup(value = "Billing", description = "User has paid or active subscription")
  USER_PAID,

  @FlagGroup("Billing")
  PREMIUM,

  @FlagGroup(value = "Verification", description = "KYC and identity verification completed")
  VERIFIED,

  @FlagGroup("UX")
  DARK_MODE,

  @FlagGroup("Beta")
  BETA_ACCESS
}
