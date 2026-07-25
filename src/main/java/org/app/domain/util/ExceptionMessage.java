package org.app.domain.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
  EMAIL_ALREADY_EXISTS("Email already exists"),
  USER_NOT_FOUND("User not found");

  private final String message;
}
