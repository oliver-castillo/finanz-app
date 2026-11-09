package org.app.finanz.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
  private BigDecimal amount;

  @Column(name = "\"CURRENCY\"")
  private String currency;

  @Column(name = "\"IS_EXCLUDED_FROM_STATISTICS\"")
  private boolean isExcludedFromStatistics;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "\"USER_ID\"", nullable = false)
  private UserEntity user;
}
