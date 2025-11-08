package com.carportal.repository;

import com.carportal.entity.ECarPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPhotoRepository extends JpaRepository<ECarPhoto, Long> {

}
