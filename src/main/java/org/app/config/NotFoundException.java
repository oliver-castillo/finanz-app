package org.app.config;

import jakarta.ws.rs.core.Response;

public class NotFoundException extends CustomException {
  public NotFoundException(ExceptionMessage exceptionMessage) {
    super(exceptionMessage, Response.Status.NOT_FOUND);
  }
}
