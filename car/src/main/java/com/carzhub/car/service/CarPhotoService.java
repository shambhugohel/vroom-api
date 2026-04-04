package com.carzhub.car.service;

import com.carzhub.car.entity.ECarPhoto;

public interface CarPhotoService {

  ECarPhoto saveCarPhoto(ECarPhoto carPhoto, Long carDetailId);

}
