package com.example.githubapi.Exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundExceptions extends GitHubExceptions{
    public UserNotFoundExceptions(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
