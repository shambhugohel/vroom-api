package com.carportal.entity;

import com.carportal.JpaConverter.OwnerConverter;
import com.carportal.constants.ApplicationConstants;
import com.carportal.constants.ApplicationConstants.Db;
import com.carportal.entity.common.AuditableEntity;
import com.carportal.enums.Owner;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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
@Table(schema = Db.TBL_SCHEMA_CAR_PORTAL, name = Db.TBL_CAR_DETAILS)
@AttributeOverride(name = ApplicationConstants.Column.ID, column = @Column(name = ApplicationConstants.Column.CAR_DETAIL_ID))
public class ECarDetails extends AuditableEntity {

  @ToString.Include
  @EqualsAndHashCode.Include
  @Column(name = ApplicationConstants.Column.CAR_MANUFACTURER, nullable = false, length = 50)
  private String carManufacturer;

  @Column(name = ApplicationConstants.Column.CAR_MODEL, nullable = false, length = 75)
  private String carModel;

  @Column(name = ApplicationConstants.Column.PRICE, nullable = false)
  private BigDecimal price;

  @Column(name = ApplicationConstants.Column.MANUFACTURED_YEAR, nullable = false)
  private int manufacturedYear;

  @Column(name = ApplicationConstants.Column.REGISTRATION_YEAR, nullable = false)
  private int registrationYear;

  @Column(name = ApplicationConstants.Column.KILOMETER_DRIVEN, nullable = false)
  private long kiloMeterDriven;

  @Column(name = ApplicationConstants.Column.DESCRIPTION, nullable = false)
  private String description;

  @Column(name = ApplicationConstants.Column.OWNER, nullable = false)
  @Convert(converter = OwnerConverter.class)
  private Owner owner;

  @OneToOne(mappedBy = "eCarDetails", cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
  private ECarEngine eCarEngine;

  @OneToOne(mappedBy = "eCarDetails", cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
  private ECarFeatures eCarFeatures;

  @OneToOne(mappedBy = "eCarDetails", cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
  private ECarOuter eCarOuter;

  public static ECarEngine newInstanceForCreation() {
    return new ECarEngine();
  }

}
