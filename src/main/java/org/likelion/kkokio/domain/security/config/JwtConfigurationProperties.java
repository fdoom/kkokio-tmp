package org.likelion.kkokio.domain.security.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter @Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigurationProperties implements InitializingBean {
    private static final String DEFAULT_SECRET = "thisisdefaultsecretkeyshouldnotbeusedinproduction";
    private static final Long DEFAULT_EXPIRATION = 3600L; // 1 hour

    private String secretKey = DEFAULT_SECRET;
    private Long expirationSeconds = DEFAULT_EXPIRATION;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("Secret key must not be null or empty.");
        }
        if (expirationSeconds == null || expirationSeconds <= 0) {
            throw new IllegalArgumentException("Expiration time must be greater than 0.");
        }

        if (secretKey.equals(DEFAULT_SECRET)) {
            log.warn("You are using default secret key. Please change it in production environment.");
        }
        if (expirationSeconds.equals(DEFAULT_EXPIRATION)) {
            log.warn("You are using default expiration time. Please change it in production environment.");
        }
    }
}
