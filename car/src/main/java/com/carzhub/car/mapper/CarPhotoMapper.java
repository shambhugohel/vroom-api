package com.carzhub.car.mapper;

import com.carzhub.car.entity.ECarPhoto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ECarPhoto.class)
public interface CarPhotoMapper {

}
