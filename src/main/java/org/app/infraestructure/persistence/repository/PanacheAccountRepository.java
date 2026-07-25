package org.app.infraestructure.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.infraestructure.persistence.entity.AccountEntity;

@ApplicationScoped
public class PanacheAccountRepository implements PanacheRepository<AccountEntity> {
}
