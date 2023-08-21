package com.example.githubapi.Exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GitHubExceptions{
    public UserNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
