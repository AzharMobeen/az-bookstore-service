package com.az.bookstore.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Azhar Mobeen
 * @since 29/09/2022
 */

@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final HeaderInterceptor headerInterceptor;
    private static final String[] WHITE_LIST_URI = {"/v3/api-docs/**", "/swagger-ui/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerInterceptor).excludePathPatterns(WHITE_LIST_URI);
    }
}
