package org.app.domain.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.app.config.AlreadyExistsException;
import org.app.config.ExceptionMessage;
import org.app.config.SecurityConfig;
import org.app.domain.model.User;
import org.app.domain.repository.UserRepository;
import org.app.domain.service.UserService;

@ApplicationScoped
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
  private final UserRepository userRepository;
  private final SecurityConfig securityConfig;

  @Override
  public User createUser(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new AlreadyExistsException(ExceptionMessage.EMAIL_ALREADY_EXISTS);
    }
    String encryptedPassword = securityConfig.hash(user.getPassword());
    user.setPassword(encryptedPassword);
    return userRepository.persist(user);
  }

  @Override
  public void updateUser(Long userId, User user) {

  }

  @Override
  public User getUserById(Long userId) {
    return null;
  }
}
