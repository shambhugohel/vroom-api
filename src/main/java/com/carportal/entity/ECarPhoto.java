package com.carportal.entity;

import com.carportal.constants.ApplicationConstants;
import com.carportal.constants.ApplicationConstants.Db;
import com.carportal.entity.common.AuditableEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
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
@Table(schema = ApplicationConstants.Db.TBL_SCHEMA_CAR_PORTAL, name = Db.TBL_CAR_PHOTOS, indexes = {
    @Index(name = ApplicationConstants.Index.IDX_CAR_PHOTO_BUSINESS_ID, columnList = ApplicationConstants.Column.BUSINESS_ID),
    @Index(name = ApplicationConstants.Index.IDX_CAR_PHOTO_IS_ACTIVE, columnList = ApplicationConstants.Column.IS_ACTIVE)
})
@AttributeOverride(name = ApplicationConstants.Column.ID, column = @Column(name = ApplicationConstants.Column.CAR_PHOTOS_ID))
@SequenceGenerator(name = ApplicationConstants.Sequence.SEQ_GEN_NAME, sequenceName = ApplicationConstants.Sequence.CAR_PHOTO_SEQ)
public class ECarPhoto extends AuditableEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  private ECarDetails ECarDetails;

}
