package org.likelion.kkokio.domain.security;

import org.likelion.kkokio.domain.security.jwt.JwtConvertable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthentication implements Authentication {
    private final JwtConvertable jwtConvertable;

    public JwtAuthentication(JwtConvertable jwtConvertable) {
        this.jwtConvertable = jwtConvertable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return jwtConvertable.getRoles().stream()
                .map(s -> new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return s;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return jwtConvertable;
    }

    @Override
    public Object getPrincipal() {
        return jwtConvertable;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return String.valueOf(jwtConvertable.getUserId());
    }
}
