package com.carzhub.shared.core.flags.internal;

import com.carzhub.shared.core.flags.api.Flag;
import java.util.EnumSet;

/**
 * Immutable bit-flag set. Fast, efficient, thread-safe and perfect for DB + caching.
 */
public final class FlagSet<F extends Enum<F> & Flag> {

  private final byte[] bitmap;
  private final Class<F> type;

  public FlagSet(Class<F> type, byte[] bytes) {
    this.type = type;
    this.bitmap = bytes.clone(); // immutability guarantee
  }

  /**
   * Check if flag is enabled
   */
  public boolean contains(F flag) {
    int bit = flag.ordinal();
    int idx = bit >> 3; // bit / 8
    int mask = 1 << (bit & 7); // bit % 8
    return (bitmap[idx] & mask) != 0;
  }

  /**
   * Safe deep copy
   */
  public byte[] toByteArray() {
    return bitmap.clone();
  }

  /**
   * Export as EnumSet for readability
   */
  public EnumSet<F> toEnumSet() {
    EnumSet<F> set = EnumSet.noneOf(type);
    for (F f : type.getEnumConstants()) {
      if (contains(f)) {
        set.add(f);
      }
    }
    return set;
  }

  @Override
  public String toString() {
    return "FlagSet" + toEnumSet();
  }
}
