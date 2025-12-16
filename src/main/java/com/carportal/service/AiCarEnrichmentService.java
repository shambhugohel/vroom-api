package com.carportal.service;

import com.carportal.api.rest.dto.CarDetailsDTO;
import com.carportal.entity.ECarDetails;
import com.carportal.entity.ECarEngine;
import com.carportal.entity.ECarFeatures;
import com.carportal.entity.ECarOuter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiCarEnrichmentService {

  private final ChatClient.Builder chatClientBuilder;

  public ECarDetails enrichCarDetails(CarDetailsDTO partialDetails) {
    log.info("Enriching car details for: {} {} {}", partialDetails.getCarManufacturer(),
        partialDetails.getCarModel(), partialDetails.getManufacturedYear());

    var chatClient = chatClientBuilder.build();

    // We want the AI to return a structured object containing the missing pieces.
    // Since ECarDetails is the root, we can try to map to it, or map to a wrapper
    // DTO.
    // For simplicity, let's ask for a wrapper that contains Engine, Features, and
    // Outer specs.

    String prompt = String.format("""
            I have a car with the following details:
            Manufacturer: %s
            Model: %s
            Year: %d
            Description/Variant: %s
            
            Please provide the technical specifications for this car, specifically:
            1. Engine details (type, displacement, power, torque, fuel type, transmission)
            2. Features (safety, interior, exterior, comfort)
            3. Outer dimensions (length, width, height, wheelbase)
            
            Return the data strictly in the requested JSON format.
            """,
        partialDetails.getCarManufacturer(),
        partialDetails.getCarModel(),
        partialDetails.getManufacturedYear(),
        partialDetails.getDescription());

    // Note: In a real implementation, we would use
    // .entity(EnrichedCarResponse.class)
    // and map it back to ECarDetails. For now, we will assume the AI returns a
    // structure
    // that we can map or we'll implement the DTOs for the response.

    // Define the record locally or use the one at class level.
    // Using the one at class level to avoid shadowing warning if I remove the local
    // one.

    try {
      EnrichedCarData enrichedData = chatClient.prompt()
          .user(prompt)
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
      log.error("Error enriching car details", e);
      return null;
    }
  }

  // Helper record for structured output
  public record EnrichedCarData(ECarEngine engine, ECarFeatures features, ECarOuter outer) {

  }
}
