package com.carportal.service;

import com.carportal.entity.ECarOuter;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface CarOuterService {

  void saveCarOuter(ECarOuter entity, Long carDetailId);

  Optional<ECarOuter> findById(Long carEngineId);

  Optional<ECarOuter> updateById(ECarOuter ECarOuter, Long carOuterId);

  void deleteById(Long carDetailId);

}
