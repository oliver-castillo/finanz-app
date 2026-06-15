package org.app.persistence.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.app.domain.repository.RefreshTokenRepository;
import org.app.persistence.entity.RefreshTokenEntity;
import org.app.persistence.entity.UserEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {
  Instant expiresAt = Instant.now().plus(7, ChronoUnit.DAYS);

  @Override
  @Transactional
  public void persist(String email) {
    UserEntity userEntity = UserEntity.findByEmail(email);

    RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
    refreshTokenEntity.setUser(userEntity);
    refreshTokenEntity.setExpiresAt(expiresAt);
    refreshTokenEntity.persist();
  }
}
