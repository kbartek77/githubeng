package com.example.githubapi.Config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Accept", "application/vnd.github+json");
        };
    }
    @Bean
    public ErrorDecoder errorDecoder(){
        return new MyErrorDecoder();
    }
}
