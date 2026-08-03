package org.app.finanz.controller.mapper;

import org.app.finanz.domain.AuthResult;
import org.app.finanz.domain.RefreshToken;
import org.app.finanz.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.openapitools.model.AuthResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, imports = {Role.class, RefreshToken.class})
public interface AuthMapper {
  AuthResponse toAuthResponse(AuthResult authResult);
}
