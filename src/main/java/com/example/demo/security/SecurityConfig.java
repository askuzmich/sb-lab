package com.example.demo.security;

import com.example.demo.endpoints.auth.AuthBasicEntryPoint;
import com.example.demo.endpoints.auth.AuthBearerTokenAccessDenied;
import com.example.demo.endpoints.auth.AuthBearerTokenEntryPoint;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class SecurityConfig {

  private final RSAPublicKey rsaPublicKey;

  private final RSAPrivateKey rsaPrivateKey;

  @Value("${api.endpoint.base-url}")
  String baseUrl;

  private final AuthBasicEntryPoint authBasicEntryPoint;

  private final AuthBearerTokenEntryPoint authBearerTokenEntryPoint;

  private final AuthBearerTokenAccessDenied authBearerTokenAccessDenied;

  public SecurityConfig(
      AuthBasicEntryPoint authBasicEntryPoint,
      AuthBearerTokenEntryPoint authBearerTokenEntryPoint,
      AuthBearerTokenAccessDenied authBearerTokenAccessDenied
  ) throws NoSuchAlgorithmException {
    this.authBasicEntryPoint = authBasicEntryPoint;
    this.authBearerTokenEntryPoint = authBearerTokenEntryPoint;
    this.authBearerTokenAccessDenied = authBearerTokenAccessDenied;

    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048); // size (bits)
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    this.rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    this.rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .authorizeHttpRequests((authHTTPReq) -> {
          authHTTPReq
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, this.baseUrl + "/subEntities/**")).permitAll()
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, this.baseUrl + "/users/**")).hasAuthority("ROLE_ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, this.baseUrl + "/users")).hasAuthority("ROLE_ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.PUT, this.baseUrl + "/users/**")).hasAuthority("ROLE_ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, this.baseUrl + "/users/**")).hasAuthority("ROLE_ADMIN")

            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()

            // All other need to be authenticated
            .anyRequest().authenticated();
        })

        // for /h2-console/**
        .headers((headerConfig) -> {
          headerConfig.frameOptions((frameOptionsConfig) -> {
            frameOptionsConfig.disable();
          });
        })

        .cors(Customizer.withDefaults())

        .csrf((csrf) -> csrf.disable()) // cross-site request forgery

        .httpBasic((httpBasic) -> {
          httpBasic
              .authenticationEntryPoint(this.authBasicEntryPoint);
        })

        .oauth2ResourceServer((oauth2Server) -> {
          oauth2Server
              .authenticationEntryPoint(this.authBearerTokenEntryPoint)
              .accessDeniedHandler(this.authBearerTokenAccessDenied)
              .jwt(Customizer.withDefaults());
        })

        .sessionManagement((sm) -> {
          sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        })

        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    // JSON Web Key
    JWK jwk = new RSAKey
        .Builder(this.rsaPublicKey).privateKey(this.rsaPrivateKey)
        .build();

    JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));

    return new NimbusJwtEncoder(jwkSource);
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(this.rsaPublicKey).build();
  }

  @Bean
  JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
        jwtGrantedAuthoritiesConverter
    );

    return jwtAuthenticationConverter;
  }
}
