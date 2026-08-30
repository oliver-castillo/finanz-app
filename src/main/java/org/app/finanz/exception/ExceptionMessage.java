package org.app.finanz.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
  EMAIL_ALREADY_EXISTS("Email already exists"),
  USER_NOT_FOUND("User not found"),
  INVALID_CREDENTIALS("Email or password is incorrect");

  private final String message;
}
