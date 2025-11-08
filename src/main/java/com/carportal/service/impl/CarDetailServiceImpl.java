package com.carportal.service.impl;

import com.carportal.entity.ECarDetails;
import com.carportal.repository.CarDetailRepository;
import com.carportal.service.CarDetailService;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarDetailServiceImpl implements CarDetailService {

  private final CarDetailRepository carDetailRepository;

  @Autowired
  public CarDetailServiceImpl(CarDetailRepository carDetailRepository) {
    this.carDetailRepository = carDetailRepository;
  }

  @Override
  public ECarDetails saveCarDetail(ECarDetails eCarDetails) {
    return carDetailRepository.save(eCarDetails);
  }

  @Override
  public Collection<ECarDetails> findAll() {
    return carDetailRepository.findAll();
  }

  @Override
  public Optional<ECarDetails> findById(Long carDetailId) {
    return carDetailRepository.findById(carDetailId);
  }

  @Override
  public Optional<ECarDetails> updateById(ECarDetails ECarDetails, Long carDetailId) {
    Optional<ECarDetails> optionalCarDetails = Optional.of(ECarDetails);
    optionalCarDetails.ifPresent(car -> {
      carDetailRepository.save(ECarDetails);
    });
    return Optional.of(ECarDetails);
  }

  @Override
  public void deleteById(Long carDetailId) {
    if (carDetailRepository.findById(carDetailId).isPresent()) {
      carDetailRepository.deleteById(carDetailId);
    }
  }

}
