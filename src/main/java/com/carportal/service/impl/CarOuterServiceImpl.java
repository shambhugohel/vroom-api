package com.carportal.service.impl;

import com.carportal.entity.ECarDetails;
import com.carportal.entity.ECarOuter;
import com.carportal.repository.CarOuterRepository;
import com.carportal.service.CarDetailService;
import com.carportal.service.CarOuterService;
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
//      com.carportal.entity.ECarOuter.setCarDetails(carDetails);
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
