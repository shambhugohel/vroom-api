package com.carportal.entity;

import com.carportal.constants.ApplicationConstants;
import com.carportal.constants.ApplicationConstants.Db;
import com.carportal.entity.common.AuditableEntity;
import com.carportal.enums.Drivetrain;
import com.carportal.enums.FuelType;
import com.carportal.enums.TransmissionType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table(schema = Db.TBL_SCHEMA_CAR_PORTAL, name = Db.TBL_CAR_ENGINE)
@AttributeOverride(name = ApplicationConstants.Column.ID, column = @Column(name = ApplicationConstants.Column.CAR_ENGINE_ID))
public class ECarEngine extends AuditableEntity {

  @Column(name = ApplicationConstants.Column.MILEAGE, nullable = false)
  private double mileage;

  @Column(name = ApplicationConstants.Column.TRANSMISSION_TYPE, nullable = false)
  private TransmissionType transmissionType;

  @Column(name = ApplicationConstants.Column.ENGINE_TYPE, nullable = false)
  private String engineType;

  @Column(name = ApplicationConstants.Column.ENGINE_DISPLACEMENT, nullable = false)
  private double engineDisplacement;

  @Column(name = ApplicationConstants.Column.MAX_POWER, nullable = false)
  private double maxPower;

  @Column(name = ApplicationConstants.Column.MAX_TORQUE, nullable = false)
  private double maxTorque;

  @Column(name = ApplicationConstants.Column.NUMBER_OF_CYLINDER, nullable = false)
  private int numberOfCylinder;

  @Column(name = ApplicationConstants.Column.FUEL_TYPE, nullable = false)
  private FuelType fuelType;

  @Column(name = ApplicationConstants.Column.DRIVETRAIN, nullable = false)
  private Drivetrain drivetrain;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "carDetailId")
  private ECarDetails eCarDetails;

  public static ECarEngine newInstanceForCreation() {
    return new ECarEngine();
  }
}
