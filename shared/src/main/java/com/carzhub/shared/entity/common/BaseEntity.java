package com.carzhub.shared.entity.common;

import com.carzhub.shared.constants.ApplicationConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLRestriction;

@Getter
@MappedSuperclass
@Setter(AccessLevel.PROTECTED)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SQLRestriction("is_active = 1")
public abstract class BaseEntity implements Serializable {

  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  @Column(name = ApplicationConstants.Column.ID, nullable = false, updatable = false)
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
