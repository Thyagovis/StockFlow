package com.stockflow.StockFlowApi.shared.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class InvalidTokenException extends AuthenticationException {

    private JWTVerificationException exception;

    public InvalidTokenException(String message, JWTVerificationException exception) {
        super(message);
        this.exception = exception;
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
