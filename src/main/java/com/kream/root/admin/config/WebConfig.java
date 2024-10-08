package com.kream.root.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/ProfileImg/**")
                .addResourceLocations("file:src/main/resources/static/upload/ProfileImg/")
                .setCacheControl(CacheControl.noStore().mustRevalidate());
    }
}
