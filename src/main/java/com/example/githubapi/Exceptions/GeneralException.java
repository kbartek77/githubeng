package com.example.githubapi.Exceptions;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GeneralException {
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleGitHubException(FeignException e) {
        return ResponseEntity.status(e.status()).body(e.getMessage());
    }
}
