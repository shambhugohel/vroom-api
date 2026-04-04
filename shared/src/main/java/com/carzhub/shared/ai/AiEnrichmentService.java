package com.carzhub.shared.ai;

import com.carzhub.shared.ai.dto.EnrichmentRequest;
import com.carzhub.shared.ai.dto.EnrichmentResult;

public interface AiEnrichmentService {
    EnrichmentResult enrichCarDetails(EnrichmentRequest request);
}
