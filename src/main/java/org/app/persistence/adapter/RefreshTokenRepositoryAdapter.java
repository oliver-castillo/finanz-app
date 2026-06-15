package org.app.persistence.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.domain.model.RefreshToken;
import org.app.domain.repository.RefreshTokenRepository;
import org.app.mapper.RefreshTokenMapper;
import org.app.persistence.entity.RefreshTokenEntity;
import org.app.persistence.entity.UserEntity;
import org.app.persistence.repository.PanacheRefreshTokenRepository;
import org.app.persistence.repository.PanacheUserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {
  Instant expiresAt = Instant.now().plus(7, ChronoUnit.DAYS);

  private final PanacheUserRepository panacheUserRepository;
  private final PanacheRefreshTokenRepository panacheRefreshTokenRepository;
  private final RefreshTokenMapper refreshTokenMapper;

  @Override
  @Transactional
  public RefreshToken persist(String email) {
    UserEntity userEntity = panacheUserRepository.findByEmail(email);

    RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
    refreshTokenEntity.setUser(userEntity);
    refreshTokenEntity.setExpiresAt(expiresAt);

    panacheRefreshTokenRepository.persist(refreshTokenEntity);

    return refreshTokenMapper.toDomain(refreshTokenEntity);
  }
}
