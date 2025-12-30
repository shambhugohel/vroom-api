package com.carportal.core.flags.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FlagGroup {

  String value(); // Group name e.g. "Billing"

  String description() default ""; // Optional tooltip
}
