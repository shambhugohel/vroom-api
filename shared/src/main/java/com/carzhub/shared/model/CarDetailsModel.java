package com.carzhub.shared.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Car details model (immutable) for reactive & concurrent use")
public record CarDetailsModel(
    @Schema(description = "Car manufacturer name", example = "Toyota")
    @NotEmpty(message = "Car manufacturer must not be empty")
    String carManufacturer,

    @Schema(description = "Car model name", example = "Corolla")
    @NotEmpty(message = "Car model must not be empty")
    String carModel,

    @Schema(description = "Price in INR", example = "1350000")
    @NotNull
    @Min(0)
    Double price,

    @Schema(description = "Manufactured year", example = "2022")
    @NotNull
    @Min(1886)
    @Max(2100)
    Integer manufacturedYear,

    @Schema(description = "Registration year", example = "2023")
    @NotNull
    @Min(1886)
    @Max(2100)
    Integer registrationYear,

    @Schema(description = "Kilometers driven", example = "24000")
    @NotNull
    @Min(0)
    Long kiloMeterDriven,

    @Schema(description = "Car description", example = "Single owner, well maintained")
    @NotEmpty
    String description,

    @Schema(description = "Owner status", example = "FIRST")
    @NotNull
    String owner  // As String or Enum name for portability
) {

}
