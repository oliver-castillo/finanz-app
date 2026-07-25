package org.app.finanz.infraestructure.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.finanz.infraestructure.persistence.entity.RefreshTokenEntity;

@ApplicationScoped
public class PanacheRefreshTokenRepository implements PanacheRepository<RefreshTokenEntity> {

}
