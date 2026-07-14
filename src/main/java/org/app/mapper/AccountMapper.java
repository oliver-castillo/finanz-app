package org.app.mapper;

import org.app.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.openapitools.model.AccountRequest;
import org.openapitools.model.AccountResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface AccountMapper {
  AccountEntity toAccountEntity(AccountRequest accountRequest);

  AccountResponse toAccountResponse(AccountEntity accountEntity);
}
