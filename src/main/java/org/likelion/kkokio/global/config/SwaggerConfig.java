package org.likelion.kkokio.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@OpenAPIDefinition
@SecuritySchemes({
        @SecurityScheme(
                name = "jwtAuth",
                type = SecuritySchemeType.HTTP,
                in = SecuritySchemeIn.HEADER,
                paramName = HttpHeaders.AUTHORIZATION,
                scheme = "bearer"
        )
})
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        String jwtSchemeName = "jwtAuth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        return new OpenAPI()
                .info(new Info().title("Kkokio API").version("1.0.0")
                        .description("""
                        <h3>JWT 토큰 구조</h3>
                        <ul>
                            <li>subject: accountId</li>
                            <li>issuedAt: 발급 시간</li>
                            <li>expiration: 만료 시간</li>
                            <li>claims:</li>
                            <ol>
                                <li>username: 보여지는 이름</li>
                                <li>roles: 권한들</li>
                            </ol>
                        </ul>
                        """)
                )
                .addSecurityItem(securityRequirement)
                .components(new Components());
    }
}