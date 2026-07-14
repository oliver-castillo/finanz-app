package org.app.controller;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.app.domain.service.AccountService;
import org.openapitools.api.AccountApi;
import org.openapitools.model.AccountRequest;
import org.openapitools.model.AccountResponse;

@RequiredArgsConstructor
public class AccountController implements AccountApi {
  private final AccountService accountService;

  @Override
  public Response createAccount(AccountRequest accountRequest) {
    AccountResponse createdAccount = accountService.createAccount(accountRequest);
    return Response.ok()
        .entity(createdAccount)
        .status(Response.Status.CREATED)
        .build();
  }
}
