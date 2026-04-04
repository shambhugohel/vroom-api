package com.carzhub.shared.model;

import com.carzhub.shared.enums.Drivetrain;
import com.carzhub.shared.enums.FuelType;
import com.carzhub.shared.enums.TransmissionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Car Engine details immutable model for concurrent use")
public record CarEngineModel(

    @Schema(description = "Mileage (km/l or km/kWh)", example = "18.5")
    @Positive
    double mileage,

    @Schema(description = "Transmission type", example = "AUTOMATIC")
    @NotNull
    TransmissionType transmissionType,

    @Schema(description = "Engine type", example = "V6")
    @NotEmpty
    String engineType,

    @Schema(description = "Engine displacement (cc)", example = "1998")
    @Positive
    double engineDisplacement,

    @Schema(description = "Max power (Horsepower)", example = "150")
    @Positive
    double maxPower,

    @Schema(description = "Max torque (Nm)", example = "190")
    @Positive
    double maxTorque,

    @Schema(description = "Number of cylinders", example = "4")
    @Positive
    int numberOfCylinder,

    @Schema(description = "Fuel type", example = "PETROL")
    @NotNull
    FuelType fuelType,

    @Schema(description = "Drivetrain", example = "FWD")
    @NotNull
    Drivetrain drivetrain) {

}
