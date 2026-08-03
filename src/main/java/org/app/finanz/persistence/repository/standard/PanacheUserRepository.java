package org.app.finanz.persistence.repository.standard;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.finanz.domain.RefreshToken;
import org.app.finanz.domain.User;
import org.app.finanz.persistence.entity.RefreshTokenEntity;
import org.app.finanz.persistence.entity.UserEntity;
import org.app.finanz.persistence.repository.UserRepository;
import org.app.finanz.persistence.repository.mapper.UserEntityMapper;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class PanacheUserRepository implements UserRepository, PanacheRepository<UserEntity> {
  private final UserEntityMapper userEntityMapper;

  /**
   * Method to check if a user exists by email
   *
   * @param email the email to check
   * @return true if a user with the given email exists, false otherwise
   */
  @Override
  public boolean existsByEmail(String email) {
    return count("email", email) > 0;
  }

  /**
   * Method to save a user
   *
   * @param user the user to save
   * @return the saved user
   */
  @Override
  @Transactional
  public User persist(User user) {
    UserEntity userEntity = userEntityMapper.toUserEntity(user);
    userEntity.setPassword(BcryptUtil.bcryptHash(user.getPassword()));
    persist(userEntity);
    return userEntityMapper.toUser(userEntity);
  }

  /**
   * Method to find a user by email
   *
   * @param email the email of the user to find
   * @return the User with the given email, or null if not found
   */
  @Override
  public Optional<User> findByEmail(String email) {
    UserEntity existingUser = findUserByEmail(email);
    return Optional.of(userEntityMapper.toUser(existingUser));
  }

  @Override
  @Transactional
  public void addRefreshToken(String email, RefreshToken refreshToken) {
    UserEntity userEntity = findUserByEmail(email);
    RefreshTokenEntity refreshTokenEntity = userEntityMapper.toRefreshTokenEntity(refreshToken);
    userEntity.addRefreshToken(refreshTokenEntity);
  }

  private UserEntity findUserByEmail(String email) {
    return find("email", email).firstResult();
  }
}
