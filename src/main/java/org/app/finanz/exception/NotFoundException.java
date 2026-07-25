package org.app.finanz.exception;

import jakarta.ws.rs.core.Response;
import org.app.finanz.domain.util.ExceptionMessage;

public class NotFoundException extends CustomException {
  public NotFoundException(ExceptionMessage exceptionMessage) {
    super(exceptionMessage, Response.Status.NOT_FOUND);
  }
}
