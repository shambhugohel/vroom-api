package com.carzhub.car.service.impl;

import com.carzhub.car.entity.ECarPhoto;
import com.carzhub.car.repository.CarPhotoRepository;
import com.carzhub.car.service.CarDetailService;
import com.carzhub.car.service.CarPhotoService;
import jakarta.persistence.EntityNotFoundException;
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

  @Override
  public ECarPhoto saveCarPhoto(ECarPhoto carPhoto, Long carDetailId) {
    carDetailService.findById(carDetailId)
        .orElseThrow(() -> new EntityNotFoundException(
            "CarDetails not found with id: " + carDetailId));
    return carPhotoRepository.save(carPhoto);
  }
}

