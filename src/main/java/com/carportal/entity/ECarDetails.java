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
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(schema = Db.TBL_SCHEMA_CAR_PORTAL, name = Db.TBL_CAR_DETAILS, indexes = {
    @Index(name = ApplicationConstants.Index.IDX_CAR_DETAILS_BUSINESS_ID, columnList = ApplicationConstants.Column.BUSINESS_ID),
    @Index(name = ApplicationConstants.Index.IDX_CAR_DETAILS_IS_ACTIVE, columnList = ApplicationConstants.Column.IS_ACTIVE)
})
@AttributeOverride(name = ApplicationConstants.Column.ID, column = @Column(name = ApplicationConstants.Column.CAR_DETAIL_ID))
@SequenceGenerator(name = ApplicationConstants.Sequence.SEQ_GEN_NAME, sequenceName = ApplicationConstants.Sequence.CAR_DETAILS_SEQ)
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

  @OneToOne(mappedBy = "eCarDetails", cascade = CascadeType.ALL, orphanRemoval = true)
  private ECarEngine eCarEngine;

  @OneToOne(mappedBy = "eCarDetails", cascade = CascadeType.ALL, orphanRemoval = true)
  private ECarFeatures eCarFeatures;

  @OneToOne(mappedBy = "eCarDetails", cascade = CascadeType.ALL, orphanRemoval = true)
  private ECarOuter eCarOuter;

  public static ECarDetails newInstanceForCreation() {
    return new ECarDetails();
  }

  public void enrichWith(ECarEngine engine, ECarFeatures features, ECarOuter outer) {
    this.eCarEngine = engine;
    this.eCarFeatures = features;
    this.eCarOuter = outer;

    if (engine != null) {
      engine.setECarDetails(this);
    }
    if (features != null) {
      features.setECarDetails(this);
    }
    if (outer != null) {
      outer.setECarDetails(this);
    }
  }

}
