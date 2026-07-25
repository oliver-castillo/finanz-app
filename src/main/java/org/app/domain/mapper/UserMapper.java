package org.app.domain.mapper;

import org.app.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.SignUpRequest;
import org.openapitools.model.SignUpResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", expression = "java(org.app.domain.util.Role.USER.name())")
  UserEntity toUserEntity(SignUpRequest signUpRequest);

  SignUpResponse toSignUpResponse(UserEntity userEntity);
}
