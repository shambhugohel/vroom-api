package com.carzhub.car.repository;

import com.carzhub.car.entity.ECarDetails;
import jakarta.persistence.QueryHint;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailRepository extends JpaRepository<ECarDetails, Long> {

  @QueryHints({
      @QueryHint(name = org.hibernate.jpa.HibernateHints.HINT_FETCH_SIZE, value = "50"),
      @QueryHint(name = org.hibernate.jpa.HibernateHints.HINT_CACHEABLE, value = "true")
  })
  List<ECarDetails> findByCarManufacturer(String manufacturer);

  @EntityGraph(attributePaths = {"eCarEngine", "eCarFeatures"})
  List<ECarDetails> findByCarModel(String model);

  @QueryHints(@QueryHint(name = org.hibernate.jpa.HibernateHints.HINT_READ_ONLY, value = "true"))
  List<ECarDetails> findTop10ByOrderByCreatedAtDesc();

}
