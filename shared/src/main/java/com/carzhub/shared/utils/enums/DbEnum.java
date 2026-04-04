package com.carzhub.shared.utils.enums;

// For enums that persist a string or custom type
public interface DbEnum<T> {

  T getDbValue();          // unique value stored in DB

//  String getDescription(); // human readable

}
