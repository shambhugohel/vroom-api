package com.carzhub.car.entity;

import com.carzhub.shared.constants.ApplicationConstants;
import com.carzhub.shared.constants.ApplicationConstants.Db;
import com.carzhub.shared.entity.common.AuditableEntity;
import com.carzhub.shared.enums.Material;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(schema = Db.TBL_SCHEMA_CAR_PORTAL, name = Db.TBL_CAR_FEATURES, indexes = {
    @Index(name = ApplicationConstants.Index.IDX_CAR_FEATURES_BUSINESS_ID, columnList = ApplicationConstants.Column.BUSINESS_ID),
    @Index(name = ApplicationConstants.Index.IDX_CAR_FEATURES_IS_ACTIVE, columnList = ApplicationConstants.Column.IS_ACTIVE)
})
@AttributeOverride(name = ApplicationConstants.Column.ID, column = @Column(name = ApplicationConstants.Column.CAR_FEATURES_ID))
@SequenceGenerator(name = ApplicationConstants.Sequence.CAR_FEATURES_SEQ_GEN, sequenceName = ApplicationConstants.Sequence.CAR_FEATURES_SEQ)
public class ECarFeatures extends AuditableEntity {

  // Cabin Comfort
  @Column(name = ApplicationConstants.Column.AIR_CONDITIONER, nullable = false, columnDefinition = "boolean default false")
  private boolean airConditioner;

  @Column(name = ApplicationConstants.Column.HEATER, nullable = false, columnDefinition = "boolean default false")
  private boolean heater;

  // Steering & Interior Materials
  @Column(name = ApplicationConstants.Column.SEAT_MATERIAL, nullable = false)
  private Material seatMaterial; // Enum: LEATHER, FABRIC, SYNTHETIC

  @Column(name = ApplicationConstants.Column.STEERING_WHEEL_MATERIAL, nullable = false)
  private Material steeringWheelMaterial; // Enum: LEATHER, PLASTIC,

  // Window Controls
  @Column(name = ApplicationConstants.Column.POWER_WINDOWS, nullable = false)
  private String powerWindows;

  @Column(name = ApplicationConstants.Column.ADJUSTABLE_STEERING, nullable = false, columnDefinition = "boolean default false")
  private boolean adjustableSteering;

  @Column(name = ApplicationConstants.Column.REMOTE_TRUNK_OPENER, nullable = false, columnDefinition = "boolean default false")
  private boolean remoteTrunkOpener;

  // Rear Seats
  @Column(name = ApplicationConstants.Column.REAR_SEAT_HEADREST, nullable = false, columnDefinition = "boolean default false")
  private boolean rearSeatHeadrest;

  @Column(name = ApplicationConstants.Column.REAR_SEAT_CENTRE_ARMREST, nullable = false, columnDefinition = "boolean default false")
  private boolean rearSeatCentreArmrest;

  @Column(name = ApplicationConstants.Column.REAR_AC_VENTS, nullable = false, columnDefinition = "boolean default false")
  private boolean rearAcVents;

  // Tech Features
  @Column(name = ApplicationConstants.Column.NAVIGATION_SYSTEM, nullable = false, columnDefinition = "boolean default false")
  private boolean navigationSystem;

  @Column(name = ApplicationConstants.Column.REVERSING_CAMERA, nullable = false, columnDefinition = "boolean default false")
  private boolean reversingCamera;

  // Mirror Controls
  @Column(name = ApplicationConstants.Column.REAR_VIEW_MIRROR_TYPE, nullable = false)
  private String rearViewMirrorType;

  @Column(name = ApplicationConstants.Column.SUNROOF, nullable = false, columnDefinition = "boolean default false")
  private boolean sunRoof;

  // Safety - Brakes & Locks
  @Column(name = ApplicationConstants.Column.ANTI_LOCK_BRAKING_SYSTEM, nullable = false, columnDefinition = "boolean default false")
  private boolean antiLockBrakingSystem;

  @Column(name = ApplicationConstants.Column.CENTRAL_LOCKING, nullable = false, columnDefinition = "boolean default false")
  private boolean centralLocking;

  @Column(name = ApplicationConstants.Column.POWER_DOOR_LOCKS, nullable = false, columnDefinition = "boolean default false")
  private boolean powerDoorLocks;

  @Column(name = ApplicationConstants.Column.CHILD_SAFETY_LOCK, nullable = false, columnDefinition = "boolean default false")
  private boolean childSafetyLock;

  @Column(name = ApplicationConstants.Column.ANTI_THEFT_ALARM, nullable = false, columnDefinition = "boolean default false")
  private boolean antiTheftAlarm;

  @Column(name = ApplicationConstants.Column.AIRBAG_CONFIG, nullable = false)
  private int airbagConfig;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = ApplicationConstants.Column.CAR_DETAIL_ID)
  private ECarDetails eCarDetails;

  public static ECarFeatures newInstanceForCreation() {
    return new ECarFeatures();
  }
}
