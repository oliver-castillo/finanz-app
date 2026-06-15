package org.app.domain.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.app.config.AlreadyExistsException;
import org.app.config.ExceptionMessage;
import org.app.config.NotFoundException;
import org.app.domain.model.User;
import org.app.domain.repository.UserRepository;
import org.app.domain.service.UserService;

@ApplicationScoped
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
  private final UserRepository userRepository;

  @Override
  public User createUser(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new AlreadyExistsException(ExceptionMessage.EMAIL_ALREADY_EXISTS);
    }
    return userRepository.persist(user);
  }

  @Override
  public void updateUser(Long userId, User user) {

  }

  @Override
  public User getUserById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
  }
}
