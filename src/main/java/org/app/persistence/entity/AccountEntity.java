package org.app.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "\"ACCOUNTS\"")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity extends AuditableEntity {
  @Column(name = "\"NAME\"")
  private String name;

  @Column(name = "\"COLOR\"")
  private String color;

  @Column(name = "\"TYPE\"")
  private String type;

  @Column(name = "\"AMOUNT\"")
  private double amount;

  @Column(name = "\"CURRENCY\"")
  private String currency;

  @Column(name = "\"IS_EXCLUDED_FROM_STATISTICS\"")
  private boolean excludedFromStatistics;
}
