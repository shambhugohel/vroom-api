package com.carzhub.car.service.impl;

import com.carzhub.car.entity.ECarDetails;
import com.carzhub.car.entity.ECarEngine;
import com.carzhub.car.repository.CarEngineRepository;
import com.carzhub.car.service.CarDetailService;
import com.carzhub.car.service.CarEngineService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarEngineServiceImpl implements CarEngineService {

  private final CarEngineRepository carEngineRepository;

  private final CarDetailService carDetailService;

  @Autowired
  public CarEngineServiceImpl(CarEngineRepository carEngineRepository,
      CarDetailService carDetailService) {
    this.carEngineRepository = carEngineRepository;
    this.carDetailService = carDetailService;
  }

  @Override
  public void saveCarEngine(ECarEngine entity, Long carDetailId) {
    Optional<ECarDetails> optionalCarDetails = Optional.of(carDetailService.findById(carDetailId))
        .get();
    optionalCarDetails.ifPresent(carDetails -> {
//      entity.setCarDetails(carDetails);
      carEngineRepository.save(entity);
    });
  }

  @Override
  public Optional<ECarEngine> findById(Long carDetailId) {
    return carEngineRepository.findById(carDetailId);
  }

  @Override
  public Optional<ECarEngine> updateById(ECarEngine ECarEngine, Long carDetailId) {
    Optional<ECarDetails> optionalCarDetails = Optional.of(carDetailService.findById(carDetailId))
        .get();
    optionalCarDetails.ifPresent(carDetails -> {

      carEngineRepository.save(ECarEngine);
    });
    return Optional.of(ECarEngine);
  }

  @Override
  public void deleteById(Long carDetailsId) {
    if (carEngineRepository.findById(carDetailsId).isPresent()) {
      carEngineRepository.deleteById(carDetailsId);
    }
  }
}
