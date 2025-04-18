package fr.milleis.test.backend.exception;

import lombok.Getter;

/**
 * Custom exception for business rule violations
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String errorCode;

    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}