package org.app.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.persistence.entity.UserEntity;

@ApplicationScoped
public class PanacheUserRepository implements PanacheRepository<UserEntity> {
  public boolean existsByEmail(String email) {
    return count("email", email) > 0;
  }

  public UserEntity findByEmail(String email) {
    return find("email", email).firstResult();
  }
}
