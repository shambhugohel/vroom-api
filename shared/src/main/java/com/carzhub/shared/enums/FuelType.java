package com.carzhub.shared.enums;

import com.carzhub.shared.utils.enums.DbEnum;
import com.carzhub.shared.utils.enums.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FuelType implements DbEnum<String> {

  PETROL("PETROL", "Petrol"),
  DIESEL("DIESEL", "Diesel"),
  CNG("CNG", "CNG"),
  LPG("LPG", "LPG"),
  BIODIESEL("BIODIESEL", "Biodiesel"),
  EV("EV", "EV"),
  HYBRID("HYBRID", "Hybrid"),
  ETHANOL("ETHANOL", "Ethanol");

  private final String dbValue;     // value stored in DB
  private final String description; // value shown in UI

  public static FuelType fromDbValue(String dbValue) {
    return EnumUtil.fromDbValue(FuelType.class, dbValue);
  }

}
