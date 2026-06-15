package org.app.domain.repository;

import org.app.domain.model.RefreshToken;

public interface RefreshTokenRepository {
  RefreshToken persist(String email);
}
