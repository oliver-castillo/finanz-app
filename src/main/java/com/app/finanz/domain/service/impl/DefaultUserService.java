package com.app.finanz.domain.service.impl;

import com.app.finanz.config.AlreadyExistsException;
import com.app.finanz.config.ErrorMessage;
import com.app.finanz.config.NotFoundException;
import com.app.finanz.domain.model.User;
import com.app.finanz.domain.repository.UserRepository;
import com.app.finanz.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultUserService implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User createUser(User user) {
    validateEmail(user.getEmail(), null);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User updateUser(Long userId, User user) {
    return userRepository
        .updateProfile(userId, user.getFirstName(), user.getLastName(), user.getEmail())
        .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND));
  }

  @Override
  public User getUserById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND));
  }

  private void validateEmail(String email, Long userId) {
    boolean exists = userId == null
        ? userRepository.existsByEmail(email)
        : userRepository.existsByEmailAndIdNot(email, userId);
    if (exists) {
      throw new AlreadyExistsException(ErrorMessage.EMAIL_ALREADY_EXISTS);
    }
  }
}
