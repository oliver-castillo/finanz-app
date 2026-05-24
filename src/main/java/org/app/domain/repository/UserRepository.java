package org.app.domain.repository;

import org.app.domain.model.User;

import java.util.Optional;

public interface UserRepository {
  User persist(User user);

  Optional<User> updateProfile(Long id, String firstName, String lastName, String email);

  Optional<User> findById(Long id);

  boolean existsById(Long id);

  boolean existsByEmail(String email);

  boolean existsByEmailAndIdNot(String email, Long id);
}