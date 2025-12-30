package com.carportal.entity.common;

import com.carportal.constants.ApplicationConstants;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Audited
@MappedSuperclass
@Setter(AccessLevel.PROTECTED)
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class AuditableEntity extends BaseEntity {

  @CreatedBy
  @Column(name = ApplicationConstants.Column.CREATED_BY, nullable = false, updatable = false, length = 50)
  private String createdBy;

  @CreatedDate
  @Column(name = ApplicationConstants.Column.CREATED_AT, nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedBy
  @Column(name = ApplicationConstants.Column.UPDATED_BY, length = 50)
  private String updatedBy;

  @LastModifiedDate
  @Column(name = ApplicationConstants.Column.UPDATED_AT, nullable = false)
  private Instant updatedAt;

  @Column(name = ApplicationConstants.Column.DELETED_BY)
  private String deletedBy;

  @Column(name = ApplicationConstants.Column.DELETED_AT)
  private Instant deletedAt;

  @Version
  @Column(name = ApplicationConstants.Column.VERSION, nullable = false)
  private Long version;

}
