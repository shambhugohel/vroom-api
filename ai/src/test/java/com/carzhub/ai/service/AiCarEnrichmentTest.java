package com.carzhub.ai.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.carzhub.ai.model.EnrichedCarData;
import com.carzhub.ai.service.impl.AiCarEnrichmentService;
import com.carzhub.shared.ai.dto.EnrichmentRequest;
import com.carzhub.shared.ai.dto.EnrichmentResult;
import com.carzhub.shared.model.CarEngineModel;
import com.carzhub.shared.model.CarFeaturesModel;
import com.carzhub.shared.model.CarOuterModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.client.ChatClient.ChatClientRequestSpec;

@ExtendWith(MockitoExtension.class)
class AiCarEnrichmentTest {

  @Mock
  private ChatClient.Builder chatClientBuilder;

  @Mock
  private ChatClient chatClient;

  @Mock
  private ChatClientRequestSpec requestSpec;

  @Mock
  private CallResponseSpec responseSpec;

  private AiCarEnrichmentService service;

  @BeforeEach
  void setUp() {
    when(chatClientBuilder.build()).thenReturn(chatClient);
    service = new AiCarEnrichmentService(chatClientBuilder);
  }

  @Test
  void enrichCarDetails_shouldReturnEnrichedDetails() {
    // Arrange
    ChatClient.Builder builder = mock(ChatClient.Builder.class);
    when(builder.build()).thenReturn(chatClient);
    service = new AiCarEnrichmentService(builder);

    when(chatClient.prompt()).thenReturn(requestSpec);
    when(requestSpec.user(anyString())).thenReturn(requestSpec);
    when(requestSpec.call()).thenReturn(responseSpec);

    // Mock Models since EnrichedCarData now uses Models
    var engineModel = new CarEngineModel(18.5, com.carzhub.shared.enums.TransmissionType.AT, "V6", 1998, 150,
        190, 4, com.carzhub.shared.enums.FuelType.PETROL, com.carzhub.shared.enums.Drivetrain.FWD);
    var featuresModel = new CarFeaturesModel(true, true, com.carzhub.shared.enums.Material.LEATHER,
        com.carzhub.shared.enums.Material.LEATHER, "ALL", true, true, true, true, true, true, true, "ELECTRIC", true,
        true, true, true, true, true, 6);
    var outerModel = new CarOuterModel(com.carzhub.shared.enums.BodyType.SEDAN, "Red", 4500, 1500, 1800, 160, 500, 5,
        50, 4, com.carzhub.shared.enums.BrakeType.DISC, com.carzhub.shared.enums.BrakeType.DISC);

    var mockData = new EnrichedCarData(engineModel, featuresModel, outerModel);
    when(responseSpec.entity(EnrichedCarData.class)).thenReturn(mockData);

    // Act
    // Service now takes EnrichmentRequest, NOT CarDetailsDTO
    var request = new EnrichmentRequest("Volkswagen", "Polo", 2012, "TDI Highline");
    EnrichmentResult result = service.enrichCarDetails(request);

    // Assert
    assertNotNull(result);
    assertNotNull(result.engine());
    assertNotNull(result.features());
    assertNotNull(result.outer());
  }
}
