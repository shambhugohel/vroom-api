package com.carportal.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.carportal.api.rest.dto.CarDetailsDTO;
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
class AiCarEnrichmentServiceTest {

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
    service = new AiCarEnrichmentService(chatClientBuilder);
  }

  @Test
  void enrichCarDetails_shouldReturnEnrichedDetails() {
    // Arrange
    when(chatClientBuilder.build()).thenReturn(chatClient);
    when(chatClient.prompt()).thenReturn(requestSpec);
    when(requestSpec.user(anyString())).thenReturn(requestSpec);
    when(requestSpec.call()).thenReturn(responseSpec);

    var mockData = new AiCarEnrichmentService.EnrichedCarData(
        ECarEngine.newInstanceForCreation(),
        ECarFeatures.newInstanceForCreation(),
        ECarOuter.newInstanceForCreation());
    when(responseSpec.entity(AiCarEnrichmentService.EnrichedCarData.class)).thenReturn(mockData);

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
