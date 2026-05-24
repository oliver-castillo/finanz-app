package org.app.mapper;

import org.app.domain.model.User;
import org.app.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.CreateUserRequest;
import org.openapitools.model.UpdateUserRequest;
import org.openapitools.model.UserResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface UserMapper {
  UserEntity userToUserEntity(User user);

  UserResponse userToUserResponse(User user);

  @Mapping(target = "id", ignore = true)
  User createUserRequestToUser(CreateUserRequest createUserRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  User updateUserRequestToUser(UpdateUserRequest updateUserRequest);

  User userEntityToUser(UserEntity userEntity);
}
