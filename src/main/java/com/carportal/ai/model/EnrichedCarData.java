package com.carportal.ai.model;

import com.carportal.entity.ECarEngine;
import com.carportal.entity.ECarFeatures;
import com.carportal.entity.ECarOuter;

public record EnrichedCarData(ECarEngine engine, ECarFeatures features, ECarOuter outer) {

}
