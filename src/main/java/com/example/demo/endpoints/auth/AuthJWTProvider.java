package com.example.demo.endpoints.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Component
public class AuthJWTProvider {
  private final JwtEncoder jwtEncoder;

  public AuthJWTProvider(JwtEncoder jwtEncoder) {
    this.jwtEncoder = jwtEncoder;
  }

  public String createJWT(Authentication authentication) {
    Instant now = Instant.now();

    long expiresIn = 2; // hours

    String authorityRoles = authentication.getAuthorities()
        .stream()
        .map((roles) -> roles.getAuthority())
        .collect(Collectors.joining(" "));

    /*
      {
        "iss": "self",
        "sub": "Zhora",
        "exp": 1231231231,
        "iat": 1231231232,
        "authorities": "ROLE_ADMIN ROLE_MANAGER"
      }
    */
    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(now)
        .expiresAt(now.plus(expiresIn, ChronoUnit.HOURS))
        .subject(authentication.getName())
        .claim("authorities", authorityRoles)
        .build();

    return this.jwtEncoder
        .encode(JwtEncoderParameters.from(claimsSet))
        .getTokenValue();
  }
}
