package com.app.finanz.persistence.adapter;

import com.app.finanz.domain.model.User;
import com.app.finanz.domain.repository.UserRepository;
import com.app.finanz.mapper.UserMapper;
import com.app.finanz.persistence.entity.UserEntity;
import com.app.finanz.persistence.repository.JpaUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {

  private final JpaUserRepository jpaUserRepository;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public User save(User user) {
    UserEntity entity = userMapper.userToUserEntity(user);
    UserEntity saved = jpaUserRepository.save(entity);
    return userMapper.userEntityToUser(saved);
  }

  @Override
  @Transactional
  public Optional<User> updateProfile(Long id, String firstName, String lastName, String email) {
    return jpaUserRepository
        .findById(id)
        .map(userEntity -> {
          userEntity.setFirstName(firstName);
          userEntity.setLastName(lastName);
          userEntity.setEmail(email);
          return userMapper.userEntityToUser(userEntity);
        });
  }

  @Override
  public Optional<User> findById(Long id) {
    return jpaUserRepository.findById(id).map(userMapper::userEntityToUser);
  }

  @Override
  public boolean existsByEmail(String email) {
    return jpaUserRepository.existsByEmail(email);
  }

  @Override
  public boolean existsById(Long id) {
    return jpaUserRepository.existsById(id);
  }

  @Override
  public boolean existsByEmailAndIdNot(String email, Long id) {
    return jpaUserRepository.existsByEmailAndIdIsNot(email, id);
  }
}