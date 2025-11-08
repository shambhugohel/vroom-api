package com.carportal.service.impl;

import com.carportal.entity.ECarDetails;
import com.carportal.entity.ECarFeatures;
import com.carportal.repository.CarFeaturesRepository;
import com.carportal.service.CarDetailService;
import com.carportal.service.CarFeaturesService;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class CarFeaturesServiceImpl implements CarFeaturesService {

  private final CarFeaturesRepository carFeaturesRepository;

  private final CarDetailService carDetailService;

  @Autowired
  public CarFeaturesServiceImpl(CarFeaturesRepository carFeaturesRepository,
      CarDetailService carDetailService) {
    this.carFeaturesRepository = carFeaturesRepository;
    this.carDetailService = carDetailService;
  }

  @Override
  public void saveCarFeatures(ECarFeatures ECarFeatures, Long carDetailId) {
    Optional<ECarDetails> optionalCarDetails = Optional.of(carDetailService.findById(carDetailId))
        .get();
    optionalCarDetails.ifPresent(carDetails -> {

      carFeaturesRepository.save(ECarFeatures);
    });
  }

  @Override
  public Optional<ECarFeatures> findById(Long carDetailId) {
    return carFeaturesRepository.findById(carDetailId);
  }

  @Override
  public Optional<ECarFeatures> updateById(ECarFeatures ECarFeatures, Long carDetailId) {
    Optional<ECarDetails> optionalCarDetails = Optional.of(carDetailService.findById(carDetailId))
        .get();
    optionalCarDetails.ifPresent(carDetails -> {
//      com.carportal.entity.ECarFeatures.setCarDetails(carDetails);
      carFeaturesRepository.save(ECarFeatures);
    });
    return Optional.of(ECarFeatures);
  }

  @Override
  public void deleteById(Long carDetailId) {
    if (carFeaturesRepository.findById(carDetailId).isPresent()) {
      carFeaturesRepository.deleteById(carDetailId);
    }
  }

}
