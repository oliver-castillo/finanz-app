package com.app.finanz.domain.service;

import com.app.finanz.domain.model.User;

public interface UserService {
  User createUser(User user);

  User updateUser(Long userId, User user);

  User getUserById(Long userId);
}
