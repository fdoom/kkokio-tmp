package org.likelion.kkokio.domain.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.likelion.kkokio.domain.security.config.JwtConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Slf4j
@Service
public class JwtService {
    private final SecretKey secret;
    private final JwtParser parser;
    private final Duration expiration;

    public JwtService(JwtConfigurationProperties jwtConfigurationProperties) {
        String secretKey = jwtConfigurationProperties.getSecretKey();
        Long expirationSeconds = jwtConfigurationProperties.getExpirationSeconds();
        this.secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.expiration = Duration.ofSeconds(expirationSeconds);
        this.parser = Jwts.parser().verifyWith(secret).build();
    }

    public String createToken(@NotNull JwtConvertable jwtConvertable) {
        Assert.notNull(jwtConvertable, "JwtConvertable must not be null.");
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration.toMillis());

        return Jwts.builder().subject(jwtConvertable.getUserId().toString())
                .claim(JwtConvertable.USER_NAME_KEY, jwtConvertable.getVisibleName())
                .claim(JwtConvertable.ROLES_KEY, jwtConvertable.getRolesString())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secret)
                .compact();
    }

    public JwtConvertable parseToken(String token) throws InvalidJwtException {
        Assert.notNull(token, "Token must not be null.");
        final Jws<Claims> claims;
        try {
            claims = parser.parseSignedClaims(token);
        } catch (JwtException e) {
            log.debug("JWT parsing failed: {}", e.getMessage());
            throw new InvalidJwtException(e);
        }

        final JwtConvertable jwtConvertable;
        try {
            Claims payload = claims.getPayload();
            Long userId = Long.parseLong(payload.getSubject());
            String username = payload.get("username", String.class);
            String rolesString = payload.get("role", String.class);
            jwtConvertable = new JwtConvertable.DefaultJwtConvertable(userId, username, rolesString);
        } catch (Exception e) {
            log.debug("JWT has invalid payload: {}", e.getClass().getSimpleName());
            throw new InvalidJwtException(e);
        }
        return jwtConvertable;
    }

}
