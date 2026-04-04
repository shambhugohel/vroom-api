package com.carzhub.car.service.impl;

import com.carzhub.car.entity.ECarDetails;
import com.carzhub.car.entity.ECarOuter;
import com.carzhub.car.repository.CarOuterRepository;
import com.carzhub.car.service.CarDetailService;
import com.carzhub.car.service.CarOuterService;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class CarOuterServiceImpl implements CarOuterService {

  private final CarOuterRepository carOuterRepository;

  private final CarDetailService carDetailService;

  @Autowired
  public CarOuterServiceImpl(CarOuterRepository carOuterRepository,
      CarDetailService carDetailService) {
    this.carOuterRepository = carOuterRepository;
    this.carDetailService = carDetailService;
  }

  @Override
  public void saveCarOuter(ECarOuter entity, Long carDetailId) {
    Optional<ECarDetails> optionalCarDetails = Optional.of(carDetailService.findById(carDetailId))
        .get();
    optionalCarDetails.ifPresent(carDetails -> {
//      entity.setCarDetails(carDetails);
      carOuterRepository.save(entity);
    });
  }

  @Override
  public Optional<ECarOuter> findById(Long carDetailId) {
    return carOuterRepository.findById(carDetailId);
  }

  @Override
  public Optional<ECarOuter> updateById(ECarOuter ECarOuter, Long carDetailId) {
    Optional<ECarDetails> optionalCarDetails = Optional.of(carDetailService.findById(carDetailId))
        .get();
    optionalCarDetails.ifPresent(carDetails -> {
//      com.carzhub.car.entity.ECarOuter.setCarDetails(carDetails);
      carOuterRepository.save(ECarOuter);
    });
    return Optional.of(ECarOuter);
  }

  @Override
  public void deleteById(Long carDetailsId) {
    if (carOuterRepository.findById(carDetailsId).isPresent()) {
      carOuterRepository.deleteById(carDetailsId);
    }
  }

}
