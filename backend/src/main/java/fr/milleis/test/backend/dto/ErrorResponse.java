package fr.milleis.test.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for standardized error responses
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;

    /**
     * Generate an error code based on the error type
     * @param errorType Type of error (e.g., PROD for product, CMD for command)
     * @param errorNumber Specific error number
     * @return Formatted error code
     */
    public static String generateErrorCode(String errorType, int errorNumber) {
        return String.format("ER%s%06d", errorType, errorNumber);
    }
}