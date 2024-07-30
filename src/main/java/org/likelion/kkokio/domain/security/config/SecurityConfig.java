package org.likelion.kkokio.domain.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.security.JwtGrantAuthenticationSuccessHandler;
import org.likelion.kkokio.domain.security.filter.JwtAuthenticationFilter;
import org.likelion.kkokio.domain.security.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final static AuthenticationFailureHandler BAD_REQUEST_FAILURE_HANDLER = (request, response, exception) -> response.setStatus(HttpStatus.BAD_REQUEST.value());
    private final static AccessDeniedHandler ACCESS_DENIED_HANDLER = (request, response, accessDeniedException) -> response.setStatus(HttpStatus.FORBIDDEN.value());
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity,
            JwtGrantAuthenticationSuccessHandler jwtGrantAuthenticationSuccessHandler
    ) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> {
                    formLogin.successHandler(jwtGrantAuthenticationSuccessHandler)
                            .failureHandler(BAD_REQUEST_FAILURE_HANDLER);
                })
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                        .accessDeniedHandler(ACCESS_DENIED_HANDLER)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/account").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/store/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, objectMapper),
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}