package com.carzhub.car.dto;

import com.carzhub.shared.enums.Drivetrain;
import com.carzhub.shared.enums.FuelType;
import com.carzhub.shared.enums.TransmissionType;
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
@Schema(description = "Car Engine details DTO for REST API")
public class CarEngineDTO {

  @Schema(description = "Business ID", example = "ENG20231010123123123")
  private String businessId;

  @Schema(description = "Mileage (km/l or km/kWh)", example = "18.5")
  @Positive
  private double mileage;

  @Schema(description = "Transmission type", example = "AUTOMATIC")
  @NotNull
  private TransmissionType transmissionType;

  @Schema(description = "Engine type", example = "V6")
  @NotEmpty
  private String engineType;

  @Schema(description = "Engine displacement (cc)", example = "1998")
  @Positive
  private double engineDisplacement;

  @Schema(description = "Max power (Horsepower)", example = "150")
  @Positive
  private double maxPower;

  @Schema(description = "Max torque (Nm)", example = "190")
  @Positive
  private double maxTorque;

  @Schema(description = "Number of cylinders", example = "4")
  @Positive
  private int numberOfCylinder;

  @Schema(description = "Fuel type", example = "PETROL")
  @NotNull
  private FuelType fuelType;

  @Schema(description = "Drivetrain", example = "FWD")
  @NotNull
  private Drivetrain drivetrain;

}
