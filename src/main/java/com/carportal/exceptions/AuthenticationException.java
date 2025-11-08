package com.carportal.exceptions;

public class AuthenticationException extends RuntimeException {

  private static final long serialVersionUID = 7857578578954L;

  public AuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }

}
