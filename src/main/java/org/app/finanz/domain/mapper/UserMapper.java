package org.app.finanz.domain.mapper;

import org.app.finanz.domain.util.Role;
import org.app.finanz.infraestructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.SignUpRequest;
import org.openapitools.model.SignUpResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, imports = Role.class)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", expression = "java(Role.USER.name())")
  UserEntity toUserEntity(SignUpRequest signUpRequest);

  SignUpResponse toSignUpResponse(UserEntity userEntity);
}
