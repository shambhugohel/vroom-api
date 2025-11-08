package com.carportal.mapper;

import com.carportal.entity.ECarOuter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ECarOuter.class)
public interface CarOuterMapper {

//  CarOuterDTO toDto(ECarOuter entity);
//
//  CarOuterModel toModel(ECarOuter entity);
//
//  ECarOuter fromModel(CarOuterModel model);
//
//  ECarOuter fromDto(CarOuterDTO dto);
}
