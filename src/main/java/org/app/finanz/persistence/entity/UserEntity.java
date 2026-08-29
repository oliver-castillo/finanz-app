package org.app.finanz.persistence.entity;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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

  @Roles
  private String role;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
  )
  private List<AccountEntity> accounts = new ArrayList<>();

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY
  )
  private List<RefreshTokenEntity> refreshTokens = new ArrayList<>();

  public void addRefreshToken(RefreshTokenEntity refreshTokenEntity) {
    refreshTokens.add(refreshTokenEntity);
  }
}
