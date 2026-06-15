package org.app.persistence.entity;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "\"USERS\"")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@UserDefinition
public class UserEntity extends AuditableEntity {
  @Column(name = "\"FIRST_NAME\"")
  private String firstName;

  @Column(name = "\"LAST_NAME\"")
  private String lastName;

  @Username
  @Column(name = "\"EMAIL\"", unique = true)
  private String email;

  @Password
  @Column(name = "\"PASSWORD\"")
  private String password;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "USERS_ROLES",
      joinColumns = @jakarta.persistence.JoinColumn(name = "USER_ID"),
      inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "ROLE_ID"))
  @Roles
  public List<RoleEntity> roles = new ArrayList<>();

  /*
   * Static method to find a user by email
   * @param email the email of the user to find
   * @return the UserEntity with the given email, or null if not found
   */
  public static UserEntity findByEmail(String email) {
    return find("email", email).firstResult();
  }
}
