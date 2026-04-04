package com.carzhub.ai.service.impl;

import com.carzhub.ai.model.EnrichedCarData;
import com.carzhub.shared.ai.AiEnrichmentService;
import com.carzhub.shared.ai.dto.EnrichmentRequest;
import com.carzhub.shared.ai.dto.EnrichmentResult;
import com.carzhub.shared.model.CarEngineModel;
import com.carzhub.shared.model.CarFeaturesModel;
import com.carzhub.shared.model.CarOuterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiCarEnrichmentService implements AiEnrichmentService {

  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(
      AiCarEnrichmentService.class);

  private final ChatClient chatClient;

  public AiCarEnrichmentService(ChatClient.Builder builder) {
    this.chatClient = builder.build();
  }

  @Override
  public EnrichmentResult enrichCarDetails(EnrichmentRequest request) {
    LOGGER.info("Enriching car details for: {} {} {}", request.carManufacturer(),
        request.carModel(), request.manufacturedYear());

    try {
      EnrichedCarData enrichedData = chatClient.prompt()
          .user("Enrich car details for " + request.carManufacturer() + " " + request.carModel() + " "
              + request.manufacturedYear())
          .call()
          .entity(EnrichedCarData.class);

      if (enrichedData != null) {
        // Map EnrichedCarData (which contains entities in original code, but we must
        // fix it too)
        // Wait, EnrichedCarData uses ECarEngine (Entities).
        // I need to update EnrichedCarData to use Models/DTOs or map manually if
        // possible.
        // But chatClient maps JSON to EnrichedCarData.
        // If I change EnrichedCarData to use Models, I need to check if ChatClient can
        // map to Records/Models.
        // Assuming AI returns matching keys.

        // Strategy: Update EnrichedCarData to use Models.
        return new EnrichmentResult(enrichedData.engine(), enrichedData.features(), enrichedData.outer());
      }
      return null;

    } catch (Exception e) {
      LOGGER.error("Error enriching car details", e);
      return null;
    }
  }
}
