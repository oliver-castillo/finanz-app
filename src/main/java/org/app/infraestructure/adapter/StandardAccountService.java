package org.app.infraestructure.adapter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.domain.mapper.AccountMapper;
import org.app.domain.service.AccountService;
import org.app.infraestructure.persistence.entity.AccountEntity;
import org.app.infraestructure.persistence.repository.PanacheAccountRepository;
import org.openapitools.model.AccountRequest;
import org.openapitools.model.AccountResponse;

@ApplicationScoped
@RequiredArgsConstructor
public class StandardAccountService implements AccountService {
  private final AccountMapper accountMapper;
  private final PanacheAccountRepository accountRepository;

  @Override
  @Transactional
  public AccountResponse createAccount(AccountRequest accountRequest) {
    AccountEntity accountEntity = accountMapper.toAccountEntity(accountRequest);
    accountRepository.persist(accountEntity);
    return accountMapper.toAccountResponse(accountEntity);
  }
}
