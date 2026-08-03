package org.app.finanz.persistence.repository;

import org.app.finanz.domain.RefreshToken;
import org.app.finanz.domain.User;

import java.util.Optional;

public interface UserRepository {
  User persist(User user);

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);

  void addRefreshToken(String email, RefreshToken refreshToken);
}
