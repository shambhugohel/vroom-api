package com.carzhub.shared.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.carzhub.shared.constants.ApplicationConstants;
import com.carzhub.shared.exceptions.common.ApiError;
import com.carzhub.shared.exceptions.common.ApiErrorProperties;
import com.carzhub.shared.exceptions.common.ApiSubError;
import com.carzhub.shared.exceptions.common.ApiValidationError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionController extends ResponseEntityExceptionHandler {

  private final ApiErrorProperties properties;

  // 🔹 Override Spring MVC defaults

  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return buildError(BAD_REQUEST, "Malformed JSON request", ex, null, request);
  }


  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return buildError(BAD_REQUEST, "Missing request parameter: " + ex.getParameterName(), ex, null,
        request);
  }


  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return buildError(METHOD_NOT_ALLOWED, "Method not allowed", ex, null, request);
  }


  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return buildError(NOT_FOUND,
        "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL(),
        ex,
        null,
        request);
  }


  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    List<ApiSubError> subErrors = ex.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> new ApiValidationError(
            fieldError.getObjectName(),
            fieldError.getField(),
            fieldError.getRejectedValue(),
            fieldError.getDefaultMessage()
        ))
        .collect(Collectors.toList());

    return buildError(BAD_REQUEST, "Validation failed", ex, subErrors, request);
  }

  // 🔹 Custom Exception Handlers

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
      WebRequest request) {
    List<ApiSubError> subErrors = ex.getConstraintViolations().stream()
        .map(violation -> new ApiValidationError(
            violation.getRootBeanClass().getSimpleName(),
            violation.getPropertyPath().toString(),
            violation.getInvalidValue(),
            violation.getMessage()
        ))
        .collect(Collectors.toList());

    return buildError(BAD_REQUEST, "Validation error", ex, subErrors, request);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex,
      WebRequest request) {
    return buildError(NOT_FOUND, ex.getMessage(), ex, null, request);
  }

  @ExceptionHandler(ServiceException.class)
  protected ResponseEntity<Object> handleServiceException(ServiceException ex, WebRequest request) {
    return buildError(INTERNAL_SERVER_ERROR, ex.getMessage(), ex, null, request);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
      WebRequest request) {
    return buildError(CONFLICT, "Database error: constraint violation", ex, null, request);
  }

  @ExceptionHandler(AccessDeniedException.class)
  protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex,
      WebRequest request) {
    return buildError(FORBIDDEN, "Access denied", ex, null, request);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleGeneric(Exception ex, WebRequest request) {
    return buildError(INTERNAL_SERVER_ERROR, "Unexpected error", ex, null, request);
  }

  // 🔹 Core Builder Method
  private ResponseEntity<Object> buildError(HttpStatus status, String message, Throwable ex,
      List<ApiSubError> subErrors, WebRequest request) {
    ApiError error = ApiError.builder()
        .status(status)
        .message(message)
        .debugMessage(ex != null ? ex.getLocalizedMessage() : null)
        .subErrors(subErrors != null ? subErrors : List.of())
        .build();

    String path = ((ServletWebRequest) request).getRequest().getRequestURI();

    if (ApplicationConstants.Exception.ERROR_RES_TYPE_PROBLEM.equalsIgnoreCase(
        properties.getFormat())) {
      return ResponseEntity
          .status(status)
          .body(error.toProblemJson(path, subErrors));
    } else {
      return ResponseEntity
          .status(status)
          .body(error);
    }

  }
}
