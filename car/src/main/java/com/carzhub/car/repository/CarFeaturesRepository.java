package com.carzhub.car.repository;

import com.carzhub.car.entity.ECarFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFeaturesRepository extends JpaRepository<ECarFeatures, Long> {

}
