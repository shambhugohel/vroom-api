package com.carportal.dto;

import com.carportal.enums.Material;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Car features DTO for REST API")
public class CarFeaturesDTO {

  // Cabin Comfort
  @Schema(description = "Air conditioner present", example = "true")
  private boolean airConditioner;

  @Schema(description = "Heater present", example = "true")
  private boolean heater;

  // Steering & Interior Materials
  @Schema(description = "Material of the seats", example = "LEATHER")
  @NotNull
  private Material seatMaterial;

  @Schema(description = "Material of steering wheel", example = "LEATHER")
  @NotNull
  private Material steeringWheelMaterial;

  // Window Controls
  @Schema(description = "Power windows configuration", example = "ALL")
  private String powerWindows;

  @Schema(description = "Adjustable steering wheel", example = "true")
  private boolean adjustableSteering;

  @Schema(description = "Remote trunk opener present", example = "true")
  private boolean remoteTrunkOpener;

  // Rear Seats
  @Schema(description = "Rear seat headrest present", example = "true")
  private boolean rearSeatHeadrest;

  @Schema(description = "Rear seat centre armrest present", example = "true")
  private boolean rearSeatCentreArmrest;

  @Schema(description = "Rear AC vents present", example = "true")
  private boolean rearAcVents;

  // Tech Features
  @Schema(description = "Navigation system present", example = "true")
  private boolean navigationSystem;

  @Schema(description = "Reversing camera present", example = "true")
  private boolean reversingCamera;

  // Mirror Controls
  @Schema(description = "Rear view mirror type", example = "ELECTRIC_FOLDING")
  private String rearViewMirrorType;

  @Schema(description = "Sunroof present", example = "true")
  private boolean sunRoof;

  // Safety - Brakes & Locks
  @Schema(description = "Anti-lock braking system present", example = "true")
  private boolean antiLockBrakingSystem;

  @Schema(description = "Central locking present", example = "true")
  private boolean centralLocking;

  @Schema(description = "Power door locks present", example = "true")
  private boolean powerDoorLocks;

  @Schema(description = "Child safety lock present", example = "true")
  private boolean childSafetyLock;

  @Schema(description = "Anti-theft alarm present", example = "true")
  private boolean antiTheftAlarm;

  @Schema(description = "Airbag configuration value", example = "2")
  private int airbagConfig;

}
