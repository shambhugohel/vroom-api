package com.carportal.entity;

import com.carportal.constants.ApplicationConstants;
import com.carportal.constants.ApplicationConstants.Db;
import com.carportal.entity.common.AuditableEntity;
import com.carportal.enums.BodyType;
import com.carportal.enums.BrakeType;
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
@Setter(AccessLevel.PROTECTED)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(schema = Db.TBL_SCHEMA_CAR_PORTAL, name = Db.TBL_CAR_OUTER, indexes = {
    @Index(name = ApplicationConstants.Index.IDX_CAR_OUTER_BUSINESS_ID, columnList = ApplicationConstants.Column.BUSINESS_ID),
    @Index(name = ApplicationConstants.Index.IDX_CAR_OUTER_IS_ACTIVE, columnList = ApplicationConstants.Column.IS_ACTIVE)
})
@AttributeOverride(name = ApplicationConstants.Column.ID, column = @Column(name = ApplicationConstants.Column.CAR_OUTER_ID))
@SequenceGenerator(name = ApplicationConstants.Sequence.SEQ_GEN_NAME, sequenceName = ApplicationConstants.Sequence.CAR_OUTER_SEQ)
public class ECarOuter extends AuditableEntity {

  @Column(name = ApplicationConstants.Column.CAR_BODY_TYPE, nullable = false)
  private BodyType carBodyType; // Enum: SEDAN, SUV, HATCHBACK, COUPE, etc.

  @Column(name = ApplicationConstants.Column.COLOR, nullable = false)
  private String color;

  @Column(name = ApplicationConstants.Column.LENGTH, nullable = false)
  private double length;

  @Column(name = ApplicationConstants.Column.HEIGHT, nullable = false)
  private double height;

  @Column(name = ApplicationConstants.Column.WIDTH, nullable = false)
  private double width;

  @Column(name = ApplicationConstants.Column.GROUND_CLEARANCE, nullable = false)
  private double groundClearance;

  @Column(name = ApplicationConstants.Column.BOOT_SPACE, nullable = false)
  private double bootSpace;

  @Column(name = ApplicationConstants.Column.SEATING_CAPACITY, nullable = false)
  private int seatingCapacity;

  @Column(name = ApplicationConstants.Column.FUEL_TANK_CAPACITY, nullable = false)
  private double fuelTankCapacity;

  @Column(name = ApplicationConstants.Column.NO_OF_DOORS, nullable = false)
  private int noOfDoors;

  @Column(name = ApplicationConstants.Column.FRONT_BRAKE_TYPE, nullable = false)
  private BrakeType frontBrakeType; // Enum: DISC, DRUM

  @Column(name = ApplicationConstants.Column.REAR_BRAKE_TYPE, nullable = false)
  private BrakeType rearBrakeType; // Enum: DISC, DRUM

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "car_detail_id")
  private ECarDetails eCarDetails;

  public static ECarOuter newInstanceForCreation() {
    return new ECarOuter();
  }
}
