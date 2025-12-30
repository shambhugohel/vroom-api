package com.carportal.ai.service.impl;

import com.carportal.ai.model.EnrichedCarData;
import com.carportal.dto.CarDetailsDTO;
import com.carportal.entity.ECarDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiCarEnrichmentService {

  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(
      AiCarEnrichmentService.class);

  private final ChatClient chatClient;

  public ECarDetails enrichCarDetails(CarDetailsDTO partialDetails) {
    LOGGER.info("Enriching car details for: {} {} {}", partialDetails.getCarManufacturer(),
        partialDetails.getCarModel(), partialDetails.getManufacturedYear());

    try {
      EnrichedCarData enrichedData = chatClient.prompt()
          .user("")
          .call()
          .entity(EnrichedCarData.class);

      if (enrichedData != null) {
        ECarDetails enrichedDetails = ECarDetails.newInstanceForCreation();
        enrichedDetails.enrichWith(enrichedData.engine(), enrichedData.features(),
            enrichedData.outer());
        return enrichedDetails;
      }
      return null;

    } catch (Exception e) {
      LOGGER.error("Error enriching car details", e);
      return null;
    }
  }
}
