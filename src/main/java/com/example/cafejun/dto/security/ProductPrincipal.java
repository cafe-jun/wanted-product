package com.example.cafejun.dto.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public record ProductPrincipal (
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname,
        String memo
) implements UserDetails {

    public ProductPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, authorities, null, null, null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return authorities;}

    @Override public String getPassword() {return password;}
    @Override public String getUsername() {return username;}

    @Override public boolean isAccountNonExpired() {return false;}

    @Override public boolean isAccountNonLocked() {return false;}

    @Override public boolean isCredentialsNonExpired() {return false;}

    @Override public boolean isEnabled() {return false;}
}
