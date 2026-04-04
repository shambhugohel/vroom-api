package com.carzhub.shared.enums;

import com.carzhub.shared.utils.enums.DbEnum;
import com.carzhub.shared.utils.enums.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransmissionType implements DbEnum<String> {
  MT("Manual Transmission", "MT"),
  AT("Automatic Transmission", "AT"),
  TC("Torque Converter", "TC"),
  CVT("Continuously Variable Transmission", "CVT"),
  DCT("Dual-Clutch Transmission", "DCT"),
  AMT("Automated Manual Transmission", "AMT");

  private final String dbValue;     // value stored in DB
  private final String description; // value shown in UI

  public static TransmissionType fromDbValue(String dbValue) {
    return EnumUtil.fromDbValue(TransmissionType.class, dbValue);
  }

}
