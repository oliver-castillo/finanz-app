package org.app.finanz.controller.mapper;

import org.app.finanz.domain.Role;
import org.app.finanz.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.SignUpRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, imports = Role.class)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", expression = "java(Role.USER)")
  @Mapping(target = "refreshTokens", ignore = true)
  User toUser(SignUpRequest signUpRequest);
}
