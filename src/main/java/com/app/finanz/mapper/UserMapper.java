package com.app.finanz.mapper;

import com.app.finanz.domain.model.User;
import com.app.finanz.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.CreateUserRequest;
import org.openapitools.model.UpdateUserRequest;
import org.openapitools.model.UserResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
  @Mapping(target = "id", source = "id")
  @Mapping(target = "firstName", source = "firstName")
  @Mapping(target = "lastName", source = "lastName")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "password", source = "password")
  UserEntity userToUserEntity(User user);

  UserResponse userToUserResponse(User user);

  @Mapping(target = "id", ignore = true)
  User createUserRequestToUser(CreateUserRequest createUserRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  User updateUserRequestToUser(UpdateUserRequest updateUserRequest);

  User userEntityToUser(UserEntity userEntity);
}
