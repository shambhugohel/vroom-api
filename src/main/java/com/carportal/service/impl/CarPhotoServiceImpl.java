package com.carportal.service.impl;

import com.carportal.repository.CarPhotoRepository;
import com.carportal.service.CarDetailService;
import com.carportal.service.CarPhotoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class CarPhotoServiceImpl implements CarPhotoService {

  private final CarPhotoRepository carPhotoRepository;

  private final CarDetailService carDetailService;

  @Autowired
  public CarPhotoServiceImpl(CarPhotoRepository carPhotoRepository,
      CarDetailService carDetailService) {
    this.carPhotoRepository = carPhotoRepository;
    this.carDetailService = carDetailService;
  }

}
