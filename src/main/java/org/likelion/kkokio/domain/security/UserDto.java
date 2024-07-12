package org.likelion.kkokio.domain.security;

import lombok.AllArgsConstructor;
import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.security.jwt.JwtConvertable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDto implements UserDetails, JwtConvertable {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private final Long userId;
    private final String username;
    private final String password;
    private final List<String> roles;

    public static UserDto ofAdmin(AdminAccount adminAccount) {
        return new UserDto(adminAccount.getAccountId(), adminAccount.getAccountLoginId(), adminAccount.getAccountLoginPassword(), List.of(ROLE_ADMIN));
    }


    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> (GrantedAuthority) () -> role).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public List<String> getRoles() {
        return roles;
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
        return true;
    }
}
