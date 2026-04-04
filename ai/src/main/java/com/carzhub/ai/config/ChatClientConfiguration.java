package com.carzhub.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfiguration {

  public static String CHAT_SYSTEM = """
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
      """;

  public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
    return chatClientBuilder
        .defaultSystem(CHAT_SYSTEM)
        .build();
  }

}
