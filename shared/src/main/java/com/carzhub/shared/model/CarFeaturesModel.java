package com.carzhub.shared.model;

import com.carzhub.shared.enums.Material;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Car features immutable model for concurrent use")
public record CarFeaturesModel(

    // Cabin Comfort
    boolean airConditioner,

    boolean heater,

    // Steering & Interior Materials
    Material seatMaterial,

    Material steeringWheelMaterial,

    // Window Controls
    String powerWindows,

    boolean adjustableSteering,

    boolean remoteTrunkOpener,

    // Rear Seats
    boolean rearSeatHeadrest,

    boolean rearSeatCentreArmrest,

    boolean rearAcVents,

    // Tech Features
    boolean navigationSystem,

    boolean reversingCamera,

    // Mirror Controls
    String rearViewMirrorType,

    boolean sunRoof,

    // Safety - Brakes & Locks
    boolean antiLockBrakingSystem,

    boolean centralLocking,

    boolean powerDoorLocks,

    boolean childSafetyLock,

    boolean antiTheftAlarm,

    int airbagConfig
) {

}
