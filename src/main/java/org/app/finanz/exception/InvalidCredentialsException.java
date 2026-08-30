package org.app.finanz.exception;

import jakarta.ws.rs.core.Response.Status;

public class InvalidCredentialsException extends CustomException {
  public InvalidCredentialsException() {
    super(ExceptionMessage.INVALID_CREDENTIALS, Status.UNAUTHORIZED);
  }
}
