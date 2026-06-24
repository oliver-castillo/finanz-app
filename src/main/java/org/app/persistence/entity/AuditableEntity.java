package org.app.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
@Getter
public abstract class AuditableEntity extends BaseEntity {
  @CreationTimestamp
  @Column(name = "\"CREATED_AT\"", nullable = false)
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "\"UPDATED_AT\"", nullable = false)
  private Instant updatedAt;

  @Column(name = "\"IS_DELETED\"", nullable = false)
  private boolean isDeleted = Boolean.FALSE;

  public void markAsDeleted() {
    this.isDeleted = Boolean.TRUE;
  }
}
