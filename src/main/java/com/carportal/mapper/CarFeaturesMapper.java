package com.carportal.mapper;

import com.carportal.entity.ECarFeatures;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ECarFeatures.class)
public interface CarFeaturesMapper {

//  CarFeaturesDTO toDto(ECarFeatures entity);
//
//  CarFeaturesModel toModel(ECarFeatures entity);
//
//  ECarFeatures fromModel(CarFeaturesModel model);
//
//  ECarFeatures fromDto(CarFeaturesDTO dto);

}
