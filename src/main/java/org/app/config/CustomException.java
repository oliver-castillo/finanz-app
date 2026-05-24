package org.app.config;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException {
  private final Response.Status status;

  protected CustomException(ExceptionMessage exceptionMessage, Response.Status status) {
    super(exceptionMessage.getMessage());
    this.status = status;
  }
}
