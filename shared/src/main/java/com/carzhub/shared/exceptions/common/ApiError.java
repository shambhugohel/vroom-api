package com.carzhub.shared.exceptions.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@Builder
public class ApiError {

  HttpStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  LocalDateTime timestamp;

  String message;
  String debugMessage;
  List<ApiSubError> subErrors;

  @Builder.Default
  LocalDateTime now = LocalDateTime.now();

  // ✅ Convert to ProblemJson DTO
  public ProblemJson toProblemJson(String path, List<ApiSubError> subErrors) {
    return ProblemJson.builder()
        .type("about:blank") // You can replace with URIs per error type
        .title(status.getReasonPhrase())
        .status(status.value())
        .detail(message)
        .instance(path)
        .invalidParams(subErrors != null && !subErrors.isEmpty() ? subErrors : null)
        .build();
  }

}
