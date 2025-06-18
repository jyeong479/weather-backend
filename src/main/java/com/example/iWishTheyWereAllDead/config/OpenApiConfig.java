package com.example.iWishTheyWereAllDead.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // JWT 인증을 위한 SecurityScheme 정의
        String jwtSchemeName = "jwtAuth";

        return new OpenAPI()
                .servers(Arrays.asList(
                        new Server().url("https://weather-backend.up.railway.app").description("Default Server")
                ))
                // Components에 SecurityScheme 추가
                .components(new Components()
                        .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                                .name(jwtSchemeName) // Swagger UI에서 Authorization 팝업에 표시될 이름
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .description("JWT 토큰을 'Bearer {token}' 형식으로 입력해주세요."))
                );
    }
}