package com.pedidoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class PedidoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidoApiApplication.class, args);
    }
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/produtos/**")
                        .uri("http://localhost:8081"))
                .route(p -> p.path("/pedidos/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
