package com.carportal.entity;

import com.carportal.constants.ApplicationConstants;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = ApplicationConstants.Db.TBL_SCHEMA_CAR_PORTAL, name = ApplicationConstants.Db.TBL_USER_CARS)
public class EUser {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
  @SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "car_portal.USER_SEQ")
  private Long userId;

}
