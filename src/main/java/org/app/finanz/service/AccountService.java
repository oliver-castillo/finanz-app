package org.app.finanz.service;

import org.openapitools.model.AccountRequest;
import org.openapitools.model.AccountResponse;

public interface AccountService {
  AccountResponse createAccount(AccountRequest accountRequest);
}
