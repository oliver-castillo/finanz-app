package org.app.finanz.domain;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RefreshToken {
  private UUID token;
  private Instant expiresAt;
  private boolean revoked;

  public static RefreshToken generateRefreshToken(Instant expiresAt) {
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setToken(UUID.randomUUID());
    refreshToken.setExpiresAt(expiresAt);
    refreshToken.setRevoked(false);
    return refreshToken;
  }
}
