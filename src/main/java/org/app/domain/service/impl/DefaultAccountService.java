package org.app.domain.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.app.domain.service.AccountService;
import org.app.mapper.AccountMapper;
import org.app.persistence.entity.AccountEntity;
import org.app.persistence.repository.PanacheAccountRepository;
import org.openapitools.model.AccountRequest;
import org.openapitools.model.AccountResponse;

@ApplicationScoped
@RequiredArgsConstructor
public class DefaultAccountService implements AccountService {
  private final AccountMapper accountMapper;
  private final PanacheAccountRepository accountRepository;

  @Override
  public AccountResponse createAccount(AccountRequest accountRequest) {
    AccountEntity accountEntity = accountMapper.toAccountEntity(accountRequest);
    accountRepository.persist(accountEntity);
    return accountMapper.toAccountResponse(accountEntity);
  }
}
