package com.carportal.dto;

import lombok.Data;

@Data
public class CarEnrichmentRequestDTO {

  private String manufacturer;
  private String model;
  private int year;
  private String variant;
  private String fuelType;
  private String transmission;
}
