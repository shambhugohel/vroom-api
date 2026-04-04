package com.carzhub.car.service;

import com.carzhub.car.entity.ECarDetails;
import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface CarDetailService {

  ECarDetails saveCarDetail(ECarDetails ECarDetails);

  Collection<ECarDetails> findAll();

  Optional<ECarDetails> findById(Long carDetailsId);

  Optional<ECarDetails> updateById(ECarDetails ECarDetails, Long carDetailsId);

  void deleteById(Long carDetailsId);

}
