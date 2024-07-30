package org.likelion.kkokio.domain.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.kkokio.domain.security.JwtAuthentication;
import org.likelion.kkokio.domain.security.jwt.InvalidJwtException;
import org.likelion.kkokio.domain.security.jwt.JwtConvertable;
import org.likelion.kkokio.domain.security.jwt.JwtService;
import org.likelion.kkokio.domain.security.jwt.TokenExpiredException;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;
import org.likelion.kkokio.global.base.exception.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final static String BEARER = "Bearer ";
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Optional<String> jwtToken = parseJwtToken(request);
        // 만약 토큰 없으면 통과
        if (jwtToken.isEmpty()) {
            log.trace("NO JWT TOKEN");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String tokenValue = jwtToken.get();

        // 토큰 있으면, 파싱해서 인증 정보 생성
        final JwtConvertable jwtConvertable;
        try {
            jwtConvertable = jwtService.parseToken(tokenValue);
        } catch (Exception e) {
            handleJwtParseException(request, response, e);
            return;
        }

        Authentication authentication = new JwtAuthentication(jwtConvertable);
        log.debug("jwt authentication created");

        // 인증 정보 등록
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(servletRequest, servletResponse);

    }

    protected void handleJwtParseException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (e instanceof CustomException customException) {
            ErrorCode errorCode = customException.getErrorCode();
            response.setStatus(errorCode.getStatus().value());
            try {
                response.setContentType("application/json;charset=UTF-8");
                String body = objectMapper.writeValueAsString(new ErrorResponse(errorCode));
                response.getWriter().println(body);
            } catch (Exception ex) {
                log.error("cant write body",ex);
                // skip body
            }
        }
    }

    protected Optional<String> parseJwtToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(BEARER)) {
            return Optional.empty();
        }
        String jwtToken = authorization.substring(7).trim();
        return Optional.of(jwtToken);
    }

}
