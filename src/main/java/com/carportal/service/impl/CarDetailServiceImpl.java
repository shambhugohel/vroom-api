package com.carportal.service.impl;

import com.carportal.api.rest.dto.CarDetailsDTO;
import com.carportal.entity.ECarDetails;
import com.carportal.repository.CarDetailRepository;
import com.carportal.service.CarDetailService;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CarDetailServiceImpl implements CarDetailService {

  private final CarDetailRepository carDetailRepository;

//  @Override
//  public ECarDetails saveCarDetail(CarDetailsDTO detailsDTO) {
//    // get details from dto and create model Cardetailsmodel
//    // check user role delaler, buyer, seller, agent, inspection,
//    // car valuation
//    // car inspection
//    // Service history fetch
//    // Accident history check
//    return null;
//  }

  @Override
  public ECarDetails saveCarDetail(ECarDetails ECarDetails) {
    return null;
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
