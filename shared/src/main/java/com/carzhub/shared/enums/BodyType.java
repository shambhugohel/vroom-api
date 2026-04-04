package com.carzhub.shared.enums;

import com.carzhub.shared.utils.enums.DbEnum;
import com.carzhub.shared.utils.enums.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BodyType implements DbEnum<String> {
  SEDAN("SEDAN", "Sedan"),
  SUV("SUV", "SUV"),
  HATCHBACK("HATCHBACK", "Hatchback"),
  COUPE("COUPE", "Coupe"),
  CONVERTIBLE("CONVERTIBLE", "Convertible"),
  WAGON("WAGON", "Wagon"),
  PICKUP("PICKUP", "Pickup Truck"),
  VAN("VAN", "Van"),
  OTHER("OTHER", "Other");

  private final String dbValue;     // value stored in DB
  private final String description; // value shown in UI

  public static BodyType fromDbValue(String dbValue) {
    return EnumUtil.fromDbValue(BodyType.class, dbValue);
  }
}
