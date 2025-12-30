package com.carportal.core.flags.util;

import com.carportal.core.flags.api.Flag;
import com.carportal.core.flags.api.FlagGroup;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class FlagMetadata {

  private FlagMetadata() {
  }

  public static <F extends Enum<F> & Flag> Map<String, List<Map<String, String>>> generate(
      Class<F> type) {

    Map<String, List<Map<String, String>>> groups = new LinkedHashMap<>();

    for (F flag : type.getEnumConstants()) {
      try {
        var field = type.getField(flag.name());
        FlagGroup annotation = field.getAnnotation(FlagGroup.class);

        String groupName = annotation != null ? annotation.value() : "General";
        String desc = annotation != null ? annotation.description() : "";

        groups
            .computeIfAbsent(groupName, g -> new ArrayList<>())
            .add(Map.of(
                "flag", flag.name(),
                "description", desc));
      } catch (NoSuchFieldException ignored) {
      }
    }

    return groups;
  }
}
