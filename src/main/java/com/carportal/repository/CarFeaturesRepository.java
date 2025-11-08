package com.carportal.repository;

import com.carportal.entity.ECarFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFeaturesRepository extends JpaRepository<ECarFeatures, Long> {

}
