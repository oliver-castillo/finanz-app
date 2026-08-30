package org.app.finanz.service.standard;

import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.app.finanz.domain.AuthResult;
import org.app.finanz.domain.RefreshToken;
import org.app.finanz.domain.User;
import org.app.finanz.exception.InvalidCredentialsException;
import org.app.finanz.service.AuthenticationService;
import org.app.finanz.service.JwtProviderService;
import org.app.finanz.service.UserService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Default implementation of the AuthenticationService interface.
 */
@ApplicationScoped
@RequiredArgsConstructor
public class StandardAuthenticationService implements AuthenticationService {
  private final UserService userService;
  private final JwtProviderService jwtProviderService;
  private final IdentityProviderManager identityProviderManager;
  private static final Instant expiresAt = Instant.now().plus(7, ChronoUnit.DAYS);

  @Override
  public AuthResult signUp(final User user) {
    userService.create(user);
    return signIn(user.getEmail(), user.getPassword());
  }

  @Override
  public AuthResult signIn(String email, String password) {
    String accessToken = generateAccessToken(email, password);
    RefreshToken refreshToken = RefreshToken.generateRefreshToken(expiresAt);

    userService.addRefreshToken(email, refreshToken);

    User signedInUser = userService.findByEmail(email);

    return generateAuthResult(accessToken, refreshToken, signedInUser);
  }

  private String generateAccessToken(String email, String password) {
    UsernamePasswordAuthenticationRequest authRequest =
        new UsernamePasswordAuthenticationRequest(
            email,
            new PasswordCredential(password.toCharArray()));

    try {
      SecurityIdentity identity = identityProviderManager
          .authenticate(authRequest)
          .await()
          .indefinitely();
      return jwtProviderService.generateAccessToken(
          identity.getPrincipal().getName(),
          identity.getRoles());
    } catch (AuthenticationFailedException _) {
      throw new InvalidCredentialsException();
    }
  }

  private AuthResult generateAuthResult(String accessToken, RefreshToken refreshToken, User signedInUser) {
    AuthResult authResult = new AuthResult();
    authResult.setAccessToken(accessToken);
    authResult.setRefreshToken(refreshToken.getToken());
    authResult.setExpiresAt(expiresAt);
    authResult.setUser(signedInUser);
    return authResult;
  }
}
