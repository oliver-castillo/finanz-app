package org.app.domain.service;

import org.app.domain.model.User;

public interface UserService {
  User createUser(User user);

  void updateUser(Long userId, User user);

  User getUserById(Long userId);
}
