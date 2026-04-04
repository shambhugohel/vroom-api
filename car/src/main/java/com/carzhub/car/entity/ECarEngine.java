package com.carzhub.car.entity;

import com.carzhub.shared.constants.ApplicationConstants;
import com.carzhub.shared.constants.ApplicationConstants.Db;
import com.carzhub.shared.entity.common.AuditableEntity;
import com.carzhub.shared.enums.Drivetrain;
import com.carzhub.shared.enums.FuelType;
import com.carzhub.shared.enums.TransmissionType;
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
@Table(schema = Db.TBL_SCHEMA_CAR_PORTAL, name = Db.TBL_CAR_ENGINE, indexes = {
    @Index(name = ApplicationConstants.Index.IDX_CAR_ENGINE_BUSINESS_ID, columnList = ApplicationConstants.Column.BUSINESS_ID),
    @Index(name = ApplicationConstants.Index.IDX_CAR_ENGINE_IS_ACTIVE, columnList = ApplicationConstants.Column.IS_ACTIVE)
})
@AttributeOverride(name = ApplicationConstants.Column.ID, column = @Column(name = ApplicationConstants.Column.CAR_ENGINE_ID))
@SequenceGenerator(name = ApplicationConstants.Sequence.CAR_ENGINE_SEQ_GEN, sequenceName = ApplicationConstants.Sequence.CAR_ENGINE_SEQ)
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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = ApplicationConstants.Column.CAR_DETAIL_ID, unique = true, nullable = false)
  private ECarDetails eCarDetails;

  public static ECarEngine newInstanceForCreation() {
    return new ECarEngine();
  }
}
