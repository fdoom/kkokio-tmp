package org.likelion.kkokio.domain.security.config;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.security.JwtGrantAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final static AuthenticationFailureHandler BAD_REQUEST_FAILURE_HANDLER = (request, response, exception) -> response.setStatus(HttpStatus.BAD_REQUEST.value());
    private final static AccessDeniedHandler ACCESS_DENIED_HANDLER = (request, response, accessDeniedException) -> response.setStatus(HttpStatus.FORBIDDEN.value());

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
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/store/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().permitAll()
                );
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}