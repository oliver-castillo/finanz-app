package org.app.finanz.infraestructure.adapter;

import org.app.finanz.domain.mapper.AccountMapper;
import org.app.finanz.infraestructure.persistence.entity.AccountEntity;
import org.app.finanz.infraestructure.persistence.repository.PanacheAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.AccountRequest;
import org.openapitools.model.AccountResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StandardAccountServiceTest {
  @Mock
  private AccountMapper accountMapper;
  @Mock
  private PanacheAccountRepository accountRepository;
  @InjectMocks
  private StandardAccountService standardAccountService;

  private AccountRequest accountRequest;
  private AccountEntity accountEntity;
  private AccountResponse accountResponse;

  @BeforeEach
  void setUp() {
    accountRequest = new AccountRequest();
    accountRequest.setName("Test Account");

    accountEntity = new AccountEntity();
    accountEntity.setName("Test Account");

    accountResponse = new AccountResponse();
    accountResponse.setId(1L);
    accountResponse.setName("Test Account");
  }

  @Test
  void shouldCreateAccountSuccessfully() {
    when(accountMapper.toAccountEntity(accountRequest)).thenReturn(accountEntity);
    when(accountMapper.toAccountResponse(accountEntity)).thenReturn(accountResponse);

    AccountResponse result = standardAccountService.createAccount(accountRequest);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("Test Account", result.getName());
    verify(accountMapper).toAccountEntity(accountRequest);
    verify(accountRepository).persist(accountEntity);
    verify(accountMapper).toAccountResponse(accountEntity);
  }

}
