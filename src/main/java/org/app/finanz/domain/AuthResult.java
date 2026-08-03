package org.app.finanz.domain;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AuthResult {
  private String accessToken;
  private UUID refreshToken;
  private Instant expiresAt;
  private User user;
}
