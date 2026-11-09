package org.app.finanz.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.finanz.persistence.entity.AccountEntity;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<AccountEntity> {

}
