package com.example.githubapi.Exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@RequiredArgsConstructor
public abstract class GitHubExceptions extends RuntimeException{
    private final HttpStatus httpStatus;
    public GitHubExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus() {
            return httpStatus;
        }
}
