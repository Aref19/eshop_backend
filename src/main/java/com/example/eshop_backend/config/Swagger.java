package com.example.eshop_backend.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    private String oauth2SecuritySchemeName = "oauth2";
    private String bearerSecuritySchemeName = "token";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info())
                .addSecurityItem(new SecurityRequirement().addList(oauth2SecuritySchemeName))
                .addSecurityItem(new SecurityRequirement().addList(bearerSecuritySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(oauth2SecuritySchemeName,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.OAUTH2)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .in(SecurityScheme.In.HEADER)
                                                .flows(new OAuthFlows().password(
                                                        new OAuthFlow().tokenUrl(
                                                                        "/auth/token"
                                                                )
                                                                .scopes(new Scopes())
                                                )))
                );

    }

    public Info info() {
        return new Info()
                .title("eshop backend")
                .description("apis ")
                .version("v1");
    }

}

