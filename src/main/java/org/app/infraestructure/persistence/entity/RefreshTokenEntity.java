package org.app.infraestructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Table(name = "REFRESH_TOKENS")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenEntity extends BaseEntity {
  @Column(nullable = false, unique = true)
  private String token;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", nullable = false)
  private UserEntity user;

  @Column(name = "EXPIRES_AT", nullable = false)
  private Instant expiresAt;

  @Column(nullable = false)
  private boolean revoked = false;

  @Column(name = "CREATED_AT", nullable = false)
  private Instant createdAt = Instant.now();
}
