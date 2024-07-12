package org.likelion.kkokio.domain.security.jwt;

import java.util.List;

public interface JwtConvertable {
    String USER_NAME_KEY = "username";
    String ROLES_KEY = "roles";

    Long getUserId();
    String getUsername();
    List<String> getRoles();
    default String getRolesString() {
        return String.join(",", getRoles());
    }

    class DefaultJwtConvertable implements JwtConvertable {
        private final Long userId;
        private final String username;
        private final List<String> roles;

        public DefaultJwtConvertable(Long userId, String username, List<String> roles) {
            this.userId = userId;
            this.username = username;
            this.roles = roles;
        }

        public DefaultJwtConvertable(Long userId, String username, String rolesString) {
            List<String> roles = List.of(rolesString.split(","));
            this.userId = userId;
            this.username = username;
            this.roles = roles;
        }

        @Override
        public Long getUserId() {
            return userId;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public List<String> getRoles() {
            return roles;
        }
    }
}
