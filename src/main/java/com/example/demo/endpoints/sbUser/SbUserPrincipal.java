package com.example.demo.endpoints.sbUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;

public class SbUserPrincipal implements UserDetails {

  private SbUser sbUser;

  public SbUserPrincipal(SbUser sbUser) {
    this.sbUser = sbUser;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays
        .stream(
            StringUtils
              .tokenizeToStringArray(this.sbUser.getRoles(), "_")
        )
        .map((role) -> {
          return new SimpleGrantedAuthority("ROLE_" + role);
        })
        .toList();
  }

  @Override
  public String getPassword() {
    return this.sbUser.getPassword();
  }

  @Override
  public String getUsername() {
    return this.sbUser.getName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.sbUser.isEnabled();
  }

  public SbUser getSbUser() {
    return sbUser;
  }
}
