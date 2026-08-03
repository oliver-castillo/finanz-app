package org.app.finanz.service;

import org.app.finanz.domain.RefreshToken;
import org.app.finanz.domain.User;

public interface UserService {
  User create(User user);

  User findByEmail(String email);

  void addRefreshToken(String email, RefreshToken refreshToken);
}
