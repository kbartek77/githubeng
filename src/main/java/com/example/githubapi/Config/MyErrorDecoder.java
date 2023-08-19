package com.example.githubapi.Config;

import com.example.githubapi.Exceptions.NotAcceptableException;
import com.example.githubapi.Exceptions.UserNotFoundExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class MyErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
            case 404:
                return new UserNotFoundExceptions(message.getMessage() != null ? message.getMessage() : "User Not Found");
            case 406:
                return new NotAcceptableException(message.getMessage() != null ? message.getMessage() : "Not Acceptable type");
            default:
                return errorDecoder .decode(methodKey, response);
        }
    }
}
