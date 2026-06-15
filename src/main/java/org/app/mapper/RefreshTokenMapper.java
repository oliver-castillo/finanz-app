package org.app.mapper;

import org.app.domain.model.RefreshToken;
import org.app.persistence.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface RefreshTokenMapper {
  RefreshToken toDomain(RefreshTokenEntity refreshTokenEntity);
}
