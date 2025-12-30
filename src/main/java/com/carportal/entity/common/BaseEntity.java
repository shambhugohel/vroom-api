package com.carportal.entity.common;

import static jakarta.persistence.GenerationType.SEQUENCE;

import com.carportal.constants.ApplicationConstants;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@MappedSuperclass
@Setter(AccessLevel.PROTECTED)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@org.hibernate.annotations.SQLRestriction("is_active = 1")
public abstract class BaseEntity implements java.io.Serializable {

  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  @Column(name = ApplicationConstants.Column.ID, nullable = false, updatable = false)
  @GeneratedValue(strategy = SEQUENCE, generator = ApplicationConstants.Sequence.SEQ_GEN_NAME)
  private Long id;

  @Column(name = ApplicationConstants.Column.BUSINESS_ID, unique = true, updatable = false, nullable = false)
  private String businessId;

  @Column(name = ApplicationConstants.Column.IS_ACTIVE, nullable = false)
  private boolean isActive = true;

  public void assignBusinessId(String businessId) {
    if (this.businessId == null) {
      this.businessId = businessId;
    }
  }

}
