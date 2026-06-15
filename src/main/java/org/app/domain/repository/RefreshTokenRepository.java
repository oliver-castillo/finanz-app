package org.app.domain.repository;

public interface RefreshTokenRepository {
  void persist(String email);
}
