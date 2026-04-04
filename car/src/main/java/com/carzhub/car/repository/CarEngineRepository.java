package com.carzhub.car.repository;

import com.carzhub.car.entity.ECarEngine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarEngineRepository extends JpaRepository<ECarEngine, Long> {

}
