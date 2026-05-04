package com.app.finanz.domain.repository;

import com.app.finanz.domain.model.User;

import java.util.Optional;

public interface UserRepository {
  User save(User user);

  Optional<User> updateProfile(Long id, String firstName, String lastName, String email);

  Optional<User> findById(Long id);

  boolean existsById(Long id);

  boolean existsByEmail(String email);

  boolean existsByEmailAndIdNot(String email, Long id);
}