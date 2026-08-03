package org.app.finanz.service.standard;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.finanz.controller.mapper.AccountMapper;
import org.app.finanz.persistence.repository.AccountRepository;
import org.app.finanz.service.AccountService;
import org.openapitools.model.AccountRequest;
import org.openapitools.model.AccountResponse;

@ApplicationScoped
@RequiredArgsConstructor
public class StandardAccountService implements AccountService {
  private final AccountMapper accountMapper;
  private final AccountRepository accountRepository;

  @Override
  @Transactional
  public AccountResponse createAccount(AccountRequest accountRequest) {
    return null;
  }
}
