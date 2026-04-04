package com.carzhub.car.repository;

import com.carzhub.car.entity.ECarPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPhotoRepository extends JpaRepository<ECarPhoto, Long> {

}
