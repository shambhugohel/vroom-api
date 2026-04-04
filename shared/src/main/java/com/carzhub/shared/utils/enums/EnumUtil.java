package com.carzhub.shared.utils.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnumUtil {

  private static final Map<Class<?>, Map<?, ?>> DB_CACHE = new ConcurrentHashMap<>();
  private static final Map<Class<?>, Map<Integer, ?>> NUMERIC_CACHE = new ConcurrentHashMap<>();

  // ----- DB value lookups -----
  private static <E extends Enum<E> & DbEnum<T>, T> Map<T, E> getDbLookup(Class<E> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants())
        .collect(Collectors.toMap(
            E::getDbValue,
            Function.identity(),
            (a, b) -> a,        // merge function (not needed, but required for type inference)
            HashMap::new              // explicit map type
        ));
  }

  public static <E extends Enum<E> & DbEnum<T>, T> E fromDbValue(Class<E> enumClass, T dbValue) {
    Map<T, E> lookup = getDbLookup(enumClass);
    return Optional.ofNullable(lookup.get(dbValue)).orElseThrow(() -> new IllegalArgumentException(
        "Unknown dbValue: " + dbValue + " for enum " + enumClass.getSimpleName()
            + ". Allowed values: " + lookup.keySet()));
  }

  // ----- Numeric value lookups -----
  private static <E extends Enum<E> & NumericDbEnum> Map<Integer, E> getNumericLookup(
      Class<E> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants()).collect(
        Collectors.toMap(
            E::getNumericValue,
            Function.identity(),
            (a, b) -> a,
            HashMap::new)
    );
  }

  public static <E extends Enum<E> & NumericDbEnum> E fromNumericValue(Class<E> enumClass,
      int numericValue) {
    Map<Integer, E> lookup = getNumericLookup(enumClass);
    return Optional.ofNullable(lookup.get(numericValue)).orElseThrow(
        () -> new IllegalArgumentException(
            "Unknown numericValue: " + numericValue + " for enum " + enumClass.getSimpleName()
                + ". Allowed values: " + lookup.keySet()));
  }

}