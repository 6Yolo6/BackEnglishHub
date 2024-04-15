package com.example.englishhub.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @Author: hahaha
 * @Date: 2024/4/11 14:09
 */

public class JwtValidationException extends AuthenticationException {
    private String statusCode;
    private String message;

    public JwtValidationException(String statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}

