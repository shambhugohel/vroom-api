package com.carzhub.shared.ai.dto;

import com.carzhub.shared.model.CarEngineModel;
import com.carzhub.shared.model.CarFeaturesModel;
import com.carzhub.shared.model.CarOuterModel;

public record EnrichmentResult(
        CarEngineModel engine,
        CarFeaturesModel features,
        CarOuterModel outer) {
}
