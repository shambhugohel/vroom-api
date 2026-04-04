package com.carzhub.shared.JpaConverter;

import com.carzhub.shared.utils.enums.DbEnum;
import com.carzhub.shared.utils.enums.EnumUtil;
import jakarta.persistence.AttributeConverter;

public abstract class GenericEnumConverter<E extends Enum<E> & DbEnum<T>, T>
    implements AttributeConverter<E, T> {

  private final Class<E> enumClass;

  protected GenericEnumConverter(Class<E> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public T convertToDatabaseColumn(E attribute) {
    return (attribute == null) ? null : attribute.getDbValue();
  }

  @Override
  public E convertToEntityAttribute(T dbValue) {
    return (dbValue == null) ? null : EnumUtil.fromDbValue(enumClass, dbValue);
  }
}
