package com.example.githubapi.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GeneralException {
    @ExceptionHandler(GitHubExceptions.class)
    public ResponseEntity<String> handleGitHubException(GitHubExceptions e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
