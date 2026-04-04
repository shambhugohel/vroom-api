package com.carzhub.shared.exceptions.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents field-level validation errors.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "Validation error details for a specific field")
public class ApiValidationError extends ApiSubError {

  @Schema(description = "The object (entity or DTO) where the validation failed", example = "Car")
  private String object;

  @Schema(description = "The field name where the error occurred", example = "manufacturer")
  private String field;

  @Schema(description = "The rejected value that caused the validation failure", example = "")
  private Object rejectedValue;

  @Schema(description = "The validation error message", example = "Manufacturer must not be blank")
  private String message;

}
