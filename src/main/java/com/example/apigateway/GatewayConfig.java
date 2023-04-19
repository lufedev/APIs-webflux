package com.example.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("pedido-api", r -> r.path("/api/pedidos/**")
                        .uri("http://localhost:8081"))
                .route("produto-api", r -> r.path("/api/produtos/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}