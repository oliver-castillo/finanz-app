package org.app.finanz.service.standard;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.app.finanz.domain.RefreshToken;
import org.app.finanz.domain.User;
import org.app.finanz.exception.AlreadyExistsException;
import org.app.finanz.exception.ExceptionMessage;
import org.app.finanz.exception.NotFoundException;
import org.app.finanz.persistence.repository.UserRepository;
import org.app.finanz.service.UserService;

@ApplicationScoped
@RequiredArgsConstructor
public class StandardUserService implements UserService {
  private final UserRepository userRepository;

  @Override
  public User create(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new AlreadyExistsException(ExceptionMessage.EMAIL_ALREADY_EXISTS);
    }
    return userRepository.persist(user);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
  }

  @Override
  public void addRefreshToken(String email, RefreshToken refreshToken) {
    if (userRepository.existsByEmail(email)) {
      userRepository.addRefreshToken(email, refreshToken);
    } else {
      throw new NotFoundException(ExceptionMessage.USER_NOT_FOUND);
    }
  }
}
