package com.carzhub.shared.utils;

import java.util.Date;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CloneUtils {

  public static Date clone(Date date) {
    if (date != null) {
      return (Date) date.clone();
    }
    return null;
  }

}
