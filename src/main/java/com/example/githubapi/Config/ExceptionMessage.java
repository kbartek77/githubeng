package com.example.githubapi.Config;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionMessage {
    private HttpStatus httpStatus;
    private String message;
}
