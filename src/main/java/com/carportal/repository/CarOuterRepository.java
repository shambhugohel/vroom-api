package com.carportal.repository;

import com.carportal.entity.ECarOuter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOuterRepository extends JpaRepository<ECarOuter, Long> {

}
