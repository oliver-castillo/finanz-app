package org.app.finanz.persistence.repository;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.finanz.persistence.entity.UserEntity;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {

  public boolean existsByUsername(String username) {
    return find("username", username.toLowerCase().trim()).firstResult() != null;
  }

  public UserEntity findByUsername(String username) {
    return find("username", username.toLowerCase().trim()).firstResult();
  }

  @Override
  public void persist(UserEntity userEntity) {
    userEntity.setPassword(BcryptUtil.bcryptHash(userEntity.getPassword().trim()));
    PanacheRepository.super.persist(userEntity);
  }
}
