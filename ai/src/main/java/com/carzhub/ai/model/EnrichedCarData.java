package com.carzhub.ai.model;

import com.carzhub.shared.model.CarEngineModel;
import com.carzhub.shared.model.CarFeaturesModel;
import com.carzhub.shared.model.CarOuterModel;

public record EnrichedCarData(CarEngineModel engine, CarFeaturesModel features, CarOuterModel outer) {

}
