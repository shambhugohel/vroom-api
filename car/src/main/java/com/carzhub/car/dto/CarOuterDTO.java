package com.carzhub.car.dto;

import com.carzhub.shared.enums.BodyType;
import com.carzhub.shared.enums.BrakeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Car outer details DTO for REST API")
public class CarOuterDTO {

  @Schema(description = "Business ID", example = "OUT20231010123123123")
  private String businessId;

  @Schema(description = "Body type", example = "SEDAN")
  @NotNull
  private BodyType carBodyType;

  @Schema(description = "Car color", example = "Black")
  @NotEmpty
  private String color;

  @Schema(description = "Length in meters", example = "4.5")
  @Positive
  private double length;

  @Schema(description = "Height in meters", example = "1.6")
  @Positive
  private double height;

  @Schema(description = "Width in meters", example = "1.8")
  @Positive
  private double width;

  @Schema(description = "Ground clearance in cm", example = "15.0")
  @Positive
  private double groundClearance;

  @Schema(description = "Boot space in liters", example = "450")
  @Positive
  private double bootSpace;

  @Schema(description = "Seating capacity", example = "5")
  @Positive
  private int seatingCapacity;

  @Schema(description = "Fuel tank capacity in liters", example = "50")
  @Positive
  private double fuelTankCapacity;

  @Schema(description = "Number of doors", example = "4")
  @Positive
  private int noOfDoors;

  @Schema(description = "Front brake type", example = "DISC")
  @NotNull
  private BrakeType frontBrakeType;

  @Schema(description = "Rear brake type", example = "DRUM")
  @NotNull
  private BrakeType rearBrakeType;
}