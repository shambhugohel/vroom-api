package com.carzhub.shared.core.flags.internal;

import com.carzhub.shared.core.flags.api.Flag;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class FastFlagBuilder<F extends Enum<F> & Flag> {

  private static final VarHandle BYTE_HANDLE;

  static {
    try {
      BYTE_HANDLE = MethodHandles.arrayElementVarHandle(byte[].class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private final byte[] bytes;
  private final Class<F> type;

  public FastFlagBuilder(Class<F> type) {
    this.type = type;
    this.bytes = new byte[(type.getEnumConstants().length + 7) / 8];
  }

  /**
   * Lock-free bit ON
   */
  public FastFlagBuilder<F> enable(F flag) {
    int bit = flag.ordinal();
    int idx = bit >> 3;
    int mask = 1 << (bit & 7);

    byte oldValue, newValue;
    do {
      oldValue = (byte) BYTE_HANDLE.getVolatile(bytes, idx);
      newValue = (byte) (oldValue | mask);
    } while (!BYTE_HANDLE.compareAndSet(bytes, idx, oldValue, newValue));

    return this;
  }

  /**
   * Lock-free bit OFF
   */
  public FastFlagBuilder<F> disable(F flag) {
    int bit = flag.ordinal();
    int idx = bit >> 3;
    int mask = ~(1 << (bit & 7));

    byte oldValue, newValue;
    do {
      oldValue = (byte) BYTE_HANDLE.getVolatile(bytes, idx);
      newValue = (byte) (oldValue & mask);
    } while (!BYTE_HANDLE.compareAndSet(bytes, idx, oldValue, newValue));

    return this;
  }

  /**
   * Create immutable FlagSet
   */
  public FlagSet<F> build() {
    return new FlagSet<>(type, bytes);
  }
}
