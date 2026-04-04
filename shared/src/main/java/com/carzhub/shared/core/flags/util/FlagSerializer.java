package com.carzhub.shared.core.flags.util;

public final class FlagSerializer {

  private FlagSerializer() {
  }

  public static String toHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02X", b));
    }
    return sb.toString();
  }

  public static byte[] fromHex(String hex) {
    int n = hex.length();
    byte[] bytes = new byte[n / 2];
    for (int i = 0; i < n; i += 2) {
      bytes[i / 2] = (byte) (Character.digit(hex.charAt(i), 16) << 4 |
          Character.digit(hex.charAt(i + 1), 16));
    }
    return bytes;
  }
}
