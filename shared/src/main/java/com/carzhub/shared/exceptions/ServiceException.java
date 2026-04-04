package com.carzhub.shared.exceptions;

import com.carzhub.shared.exceptions.common.ExceptionType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

  private final ExceptionType type;
  private final String messageCode;
  private final HttpStatus httpStatus;

  // Constructors
  public ServiceException(ExceptionType type, String message, String messageCode,
      HttpStatus httpStatus, Throwable cause) {
    super(message, cause);
    this.type = type != null ? type : ExceptionType.UNKNOWN;
    this.messageCode = messageCode != null ? messageCode : "error.unknown";
    this.httpStatus = httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public ServiceException(ExceptionType type, String message, String messageCode,
      HttpStatus httpStatus) {
    this(type, message, messageCode, httpStatus, null);
  }

  public ServiceException(ExceptionType type, String message, String messageCode) {
    this(type, message, messageCode, HttpStatus.INTERNAL_SERVER_ERROR, null);
  }

  public ServiceException(ExceptionType type, String message) {
    this(type, message, "error." + type.name().toLowerCase(), defaultStatus(type), null);
  }

  private static HttpStatus defaultStatus(ExceptionType type) {
    return switch (type) {
      case VALIDATION -> HttpStatus.BAD_REQUEST;
      case BUSINESS -> HttpStatus.valueOf(422);
      case DATABASE -> HttpStatus.CONFLICT;
      case NETWORK -> HttpStatus.GATEWAY_TIMEOUT;
      default -> HttpStatus.INTERNAL_SERVER_ERROR;
    };
  }

}
