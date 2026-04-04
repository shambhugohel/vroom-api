package com.carzhub.shared.core.flags.audit;

import com.carzhub.shared.core.flags.api.Flag;

public final class FlagChangeLogger {

  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(
      FlagChangeLogger.class);

  private FlagChangeLogger() {
  }

  public static <F extends Enum<F> & Flag> void logChange(
      String userId, F flag, boolean oldValue, boolean newValue, String actor) {
    LOGGER.info("FLAG_CHANGE user={} actor={} flag={} old={} new={}",
        userId, actor, flag, oldValue, newValue);
  }
}
