package org.app.domain.mapper;

import org.app.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.AccountRequest;
import org.openapitools.model.AccountResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface AccountMapper {
  AccountEntity toAccountEntity(AccountRequest accountRequest);

  @Mapping(target = "isExcludedFromStatistics", expression = "java(accountEntity.isExcludedFromStatistics())")
  AccountResponse toAccountResponse(AccountEntity accountEntity);
}
