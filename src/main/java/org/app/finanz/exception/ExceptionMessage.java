package org.app.finanz.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
  USERNAME_ALREADY_EXISTS("Username already exists"),
  USER_NOT_FOUND("User not found"),
  INVALID_CREDENTIALS("Username or password is incorrect");

  private final String message;
}
