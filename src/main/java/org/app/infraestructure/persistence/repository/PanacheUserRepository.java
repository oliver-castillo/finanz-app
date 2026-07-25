package org.app.infraestructure.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.infraestructure.persistence.entity.UserEntity;

@ApplicationScoped
public class PanacheUserRepository implements PanacheRepository<UserEntity> {

  /**
   * Method to check if a user exists by email
   *
   * @param email the email to check
   * @return true if a user with the given email exists, false otherwise
   */
  public boolean existsByEmail(String email) {
    return count("email", email) > 0;
  }

  /**
   * Method to find a user by email
   *
   * @param email the email of the user to find
   * @return the UserEntity with the given email, or null if not found
   */
  public UserEntity findByEmail(String email) {
    return find("email", email).firstResult();
  }
}
