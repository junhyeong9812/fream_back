package com.kream.root.MainAndShop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info =  @Info(title = "kream MainPage",
                description = "kream Main Swagger",
                version = "v1"))
@Configuration
public class SwaggerConfigMain {
    @Bean
    public GroupedOpenApi mainApi() {
        return GroupedOpenApi.builder()
                .group("MainController")
                .group("ShopController")
                .group("MainMyPageController")
                .pathsToMatch("/**") //Rest Controller로 연결할 path를 작성
                .build();
    }
}
