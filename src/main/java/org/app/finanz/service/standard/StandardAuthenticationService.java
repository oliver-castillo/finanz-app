package org.app.finanz.service.standard;

import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.finanz.config.ApplicationProperties;
import org.app.finanz.exception.AlreadyExistsException;
import org.app.finanz.exception.ExceptionMessage;
import org.app.finanz.exception.InvalidCredentialsException;
import org.app.finanz.mapper.UserMapper;
import org.app.finanz.persistence.entity.UserEntity;
import org.app.finanz.persistence.repository.UserRepository;
import org.app.finanz.service.AuthenticationService;
import org.app.finanz.service.JwtProviderService;
import org.openapitools.model.AuthResponse;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignUpRequest;

@ApplicationScoped
@RequiredArgsConstructor
public class StandardAuthenticationService implements AuthenticationService {

  private final UserRepository userRepository;
  private final ApplicationProperties applicationProperties;
  private final UserMapper userMapper;
  private final IdentityProviderManager identityProviderManager;
  private final JwtProviderService jwtProviderService;

  @Transactional
  public AuthResponse signUp(SignUpRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new AlreadyExistsException(ExceptionMessage.USERNAME_ALREADY_EXISTS);
    }

    UserEntity userEntity = userMapper.toUserEntity(request);

    userRepository.persist(userEntity);


    return null;
  }


  @Transactional
  public AuthResponse signIn(SignInRequest request) {
    String accessToken = generateAccessToken(request.getUsername(), request.getPassword());

    UserEntity userEntity = userRepository.findByUsername(request.getUsername());
    userEntity.addRefreshToken(applicationProperties.getRefreshTokenExpiration());

    return new AuthResponse().accessToken(accessToken);
  }

  private String generateAccessToken(String username, String password) {
    UsernamePasswordAuthenticationRequest authRequest =
        new UsernamePasswordAuthenticationRequest(
            username,
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
}
