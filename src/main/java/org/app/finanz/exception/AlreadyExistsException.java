package org.app.finanz.exception;

import jakarta.ws.rs.core.Response;
import org.app.finanz.domain.util.ExceptionMessage;

public class AlreadyExistsException extends CustomException {
  public AlreadyExistsException(ExceptionMessage exceptionMessage) {
    super(exceptionMessage, Response.Status.CONFLICT);
  }
}
