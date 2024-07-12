package org.likelion.kkokio.domain.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.kkokio.domain.security.jwt.JwtConvertable;
import org.likelion.kkokio.domain.security.jwt.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class JwtGrantAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication instanceof UsernamePasswordAuthenticationToken token && token.getPrincipal() instanceof JwtConvertable jwtConvertable) {
            String createdToken = jwtService.createToken(jwtConvertable);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Authorization", "Bearer " + createdToken);
        } else {
            log.error("Unexpected authentication object: {}", authentication.getClass().getSimpleName());
        }
    }
}
