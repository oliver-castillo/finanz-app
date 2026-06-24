package org.app.persistence.entity;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Roles
  public String role;
}
