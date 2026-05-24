package org.app.config;

import jakarta.ws.rs.core.Response;

public class AlreadyExistsException extends CustomException {
  public AlreadyExistsException(ExceptionMessage exceptionMessage) {
    super(exceptionMessage, Response.Status.CONFLICT);
  }
}
