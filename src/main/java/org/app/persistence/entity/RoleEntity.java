package org.app.persistence.entity;

import io.quarkus.security.jpa.RolesValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "\"ROLES\"")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends AuditableEntity {
  /*@ManyToMany(mappedBy = "roles")
  public List<UserEntity> users;*/

  @RolesValue
  private String role;
}
