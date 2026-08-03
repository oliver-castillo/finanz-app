package org.app.finanz.persistence.repository.mapper;

import org.app.finanz.domain.RefreshToken;
import org.app.finanz.domain.Role;
import org.app.finanz.domain.User;
import org.app.finanz.persistence.entity.RefreshTokenEntity;
import org.app.finanz.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, imports = Role.class)
public interface UserEntityMapper {
  @Mapping(target = "id", ignore = true)
  UserEntity toUserEntity(User user);

  User toUser(UserEntity userEntity);

  RefreshTokenEntity toRefreshTokenEntity(RefreshToken refreshToken);
}
