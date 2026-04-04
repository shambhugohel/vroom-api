package com.carzhub.shared.JpaConverter;

import com.carzhub.shared.enums.Owner;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OwnerConverter extends GenericEnumConverter<Owner, String> {

  public OwnerConverter() {
    super(Owner.class);
  }

}
