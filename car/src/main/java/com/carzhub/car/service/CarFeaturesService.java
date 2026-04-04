package com.carzhub.car.service;

import com.carzhub.car.entity.ECarFeatures;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface CarFeaturesService {

  void saveCarFeatures(ECarFeatures entity, Long carDetailId);

  Optional<ECarFeatures> findById(Long carEngineId);

  Optional<ECarFeatures> updateById(ECarFeatures ECarFeatures, Long carEngineId);

  void deleteById(Long carDetailsId);

}
