package com.carzhub.shared.enums;

import com.carzhub.shared.utils.enums.DbEnum;
import com.carzhub.shared.utils.enums.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrakeType implements DbEnum<String> {
  DISC("DISC", "Disc"),
  DRUM("DRUM", "Drum"),
  OTHER("OTHER", "Other");

  private final String dbValue;     // value stored in DB
  private final String description; // value shown in UI

  public static BrakeType fromDbValue(String dbValue) {
    return EnumUtil.fromDbValue(BrakeType.class, dbValue);
  }
}
