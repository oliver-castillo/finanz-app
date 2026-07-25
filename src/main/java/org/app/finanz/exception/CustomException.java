package org.app.finanz.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;
import org.app.finanz.domain.util.ExceptionMessage;

@Getter
public abstract class CustomException extends RuntimeException {
  private final Response.Status status;

  protected CustomException(ExceptionMessage exceptionMessage, Response.Status status) {
    super(exceptionMessage.getMessage());
    this.status = status;
  }
}
