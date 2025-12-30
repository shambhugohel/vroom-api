package com.carportal.test.ai;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.carportal.ai.model.EnrichedCarData;
import com.carportal.ai.service.impl.AiCarEnrichmentService;
import com.carportal.dto.CarDetailsDTO;
import com.carportal.entity.ECarDetails;
import com.carportal.entity.ECarEngine;
import com.carportal.entity.ECarFeatures;
import com.carportal.entity.ECarOuter;
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
    service = new AiCarEnrichmentService(chatClientBuilder.build());
  }

  @Test
  void enrichCarDetails_shouldReturnEnrichedDetails() {
    // Arrange
    when(chatClient.prompt()).thenReturn(requestSpec);
    when(requestSpec.user(anyString())).thenReturn(requestSpec);
    when(requestSpec.call()).thenReturn(responseSpec);

    var mockData = new EnrichedCarData(
        ECarEngine.newInstanceForCreation(),
        ECarFeatures.newInstanceForCreation(),
        ECarOuter.newInstanceForCreation());
    when(responseSpec.entity(EnrichedCarData.class)).thenReturn(mockData);

    var dto = new CarDetailsDTO();
    dto.setCarManufacturer("Volkswagen");
    dto.setCarModel("Polo");
    dto.setManufacturedYear(2012);
    dto.setDescription("TDI Highline");

    // Act
    ECarDetails result = service.enrichCarDetails(dto);

    // Assert
    assertNotNull(result);
    assertNotNull(result.getECarEngine());
    assertNotNull(result.getECarFeatures());
    assertNotNull(result.getECarOuter());
  }
}
