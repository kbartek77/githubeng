package com.example.githubapi.Config;

import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

public class FeingConfig {
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
