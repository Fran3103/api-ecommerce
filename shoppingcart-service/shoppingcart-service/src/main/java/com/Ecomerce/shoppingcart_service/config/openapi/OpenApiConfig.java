package com.Ecomerce.shoppingcart_service.config.openapi;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Shopping Cart Service API")
                        .version("1.0")
                        .description("API documentation for the Shopping Cart Service")
                );
    }
}
