package com.carzhub.shared.model;

import com.carzhub.shared.enums.BodyType;
import com.carzhub.shared.enums.BrakeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Car outer details immutable model for concurrency")
public record CarOuterModel(

    @NotNull
    BodyType carBodyType,

    @NotNull
    String color,

    @Positive
    double length,

    @Positive
    double height,

    @Positive
    double width,

    @Positive
    double groundClearance,

    @Positive
    double bootSpace,

    @Positive
    int seatingCapacity,

    @Positive
    double fuelTankCapacity,

    @Positive
    int noOfDoors,

    @NotNull
    BrakeType frontBrakeType,

    @NotNull
    BrakeType rearBrakeType
) {

}
