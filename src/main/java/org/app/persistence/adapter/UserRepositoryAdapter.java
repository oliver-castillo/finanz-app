package org.app.persistence.adapter;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.domain.model.User;
import org.app.domain.repository.UserRepository;
import org.app.mapper.UserMapper;
import org.app.persistence.entity.UserEntity;
import org.app.persistence.repository.PanacheUserRepository;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

  private final PanacheUserRepository panacheUserRepository;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public User persist(User user) {
    UserEntity userEntity = userMapper.userToUserEntity(user);
    userEntity.setPassword(BcryptUtil.bcryptHash(user.getPassword()));
    panacheUserRepository.persist(userEntity);
    return userMapper.userEntityToUser(userEntity);
  }

  @Override
  public Optional<User> updateProfile(Long id, String firstName, String lastName, String email) {
    return Optional.empty();
  }

  @Override
  public Optional<User> findById(Long id) {
    UserEntity userEntity = panacheUserRepository.findById(id);
    return Optional.of(userMapper.userEntityToUser(userEntity));
  }

  @Override
  public Optional<User> findByEmail(String email) {
    UserEntity userEntity = panacheUserRepository.findByEmail(email);
    User user = userMapper.userEntityToUser(userEntity);
    return Optional.of(user);
  }

  @Override
  public boolean existsById(Long id) {
    return panacheUserRepository.findById(id) != null;
  }

  @Override
  public boolean existsByEmail(String email) {
    return panacheUserRepository.existsByEmail(email);
  }

  @Override
  public boolean existsByEmailAndIdNot(String email, Long id) {
    return false;
  }
}
