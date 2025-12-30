package com.carportal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Car details DTO for REST API")
public class CarDetailsDTO {

  @Schema(description = "Car manufacturer name", example = "Toyota")
  @NotEmpty(message = "Car manufacturer must not be empty")
  private String carManufacturer;

  @Schema(description = "Car model name", example = "Corolla")
  @NotEmpty(message = "Car model must not be empty")
  private String carModel;

  @Schema(description = "Price in INR", example = "1350000")
  @NotNull
  @Min(0)
  private Double price;

  @Schema(description = "Manufactured Year", example = "2022")
  @NotNull
  @Min(1886) // first car invention year
  @Max(2100)
  private Integer manufacturedYear;

  @Schema(description = "Year of registration", example = "2023")
  @NotNull
  @Min(1886)
  @Max(2100)
  private Integer registrationYear;

  @Schema(description = "Kilometers driven", example = "24000")
  @NotNull
  @Min(0)
  private Long kiloMeterDriven;

  @Schema(description = "Car description", example = "Single owner, well maintained")
  @NotEmpty
  private String description;

  @Schema(description = "Owner status", example = "FIRST")
  @NotNull
  private String owner;  // For simplicity as string; can be enum for strong typing

}
