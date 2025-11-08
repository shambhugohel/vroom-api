package com.carportal.enums;

import com.carportal.utils.enums.DbEnum;
import com.carportal.utils.enums.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Drivetrain implements DbEnum<String> {

  FWD("FWD", "Front-wheel Drive"),
  RWD("RWD", "Rear-wheel drive"),
  //  FourWD("4WD", "All-wheel drive"),
  AWD("AWD", "Four-wheel drive");

  private final String dbValue;     // value stored in DB
  private final String description; // value shown in UI

  public static Drivetrain fromDbValue(String dbValue) {
    return EnumUtil.fromDbValue(Drivetrain.class, dbValue);
  }

}
