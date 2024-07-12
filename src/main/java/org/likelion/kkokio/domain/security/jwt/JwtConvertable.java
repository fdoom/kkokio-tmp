package org.likelion.kkokio.domain.security.jwt;

public interface JwtConvertable {
    Long getUserId();
    String getUsername();
    String getRole();

    class DefaultJwtConvertable implements JwtConvertable {
        private final Long userId;
        private final String username;
        private final String role;

        public DefaultJwtConvertable(Long userId, String username, String role) {
            this.userId = userId;
            this.username = username;
            this.role = role;
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
        public String getRole() {
            return role;
        }
    }
}
