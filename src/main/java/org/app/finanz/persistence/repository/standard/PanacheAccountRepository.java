package org.app.finanz.persistence.repository.standard;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.app.finanz.persistence.entity.AccountEntity;
import org.app.finanz.persistence.repository.AccountRepository;

@ApplicationScoped
@RequiredArgsConstructor
public class PanacheAccountRepository implements AccountRepository, PanacheRepository<AccountEntity> {
}
