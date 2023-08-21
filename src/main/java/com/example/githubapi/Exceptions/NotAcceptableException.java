package com.example.githubapi.Exceptions;

import org.springframework.http.HttpStatus;

public class NotAcceptableException extends GitHubExceptions{
    public NotAcceptableException(String message){
        super(message, HttpStatus.NOT_ACCEPTABLE);
    }
}
