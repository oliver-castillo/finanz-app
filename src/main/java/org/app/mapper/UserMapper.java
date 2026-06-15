package org.app.mapper;

import org.app.domain.model.User;
import org.app.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.SignUpRequest;
import org.openapitools.model.SignUpResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface UserMapper {
  UserEntity userToUserEntity(User user);

  SignUpResponse userToSignUpResponse(User user);

  @Mapping(target = "id", ignore = true)
  User signUpRequestToUser(SignUpRequest signUpRequest);

  /*@Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  User updateUserRequestToUser(UpdateUserRequest updateUserRequest);*/

  User userEntityToUser(UserEntity userEntity);
}
