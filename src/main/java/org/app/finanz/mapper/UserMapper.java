package org.app.finanz.mapper;

import org.app.finanz.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.openapitools.model.SignUpRequest;
import org.openapitools.model.UserBase;

@Mapper(componentModel = ComponentModel.CDI)
public interface UserMapper {
  UserEntity toUserEntity(String firstName, String lastName, String email, String password);

  UserEntity toUserEntity(SignUpRequest signUpRequest);

  UserBase toUserBase(UserEntity userEntity);


}
