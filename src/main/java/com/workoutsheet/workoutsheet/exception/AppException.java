package com.workoutsheet.workoutsheet.exception;

import com.workoutsheet.workoutsheet.constants.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public AppException(ErrorType errorType) {
        this(errorType, HttpStatus.BAD_REQUEST);
    }

    public AppException(ErrorType errorType, HttpStatus httpStatus) {
        super(errorType.getMessage());
        this.errorCode = errorType.getCode();
        this.errorMessage = errorType.getMessage();
        this.httpStatus = httpStatus;
    }

    public AppException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public static void throwIf(boolean condition, ErrorType errorType) {
        if (condition) {
            throw new AppException(errorType);
        }
    }

    public static void throwIf(boolean condition, ErrorType errorType, HttpStatus httpStatus) {
        if (condition) {
            throw new AppException(errorType, httpStatus);
        }
    }

    public static void throwIfNot(boolean condition, ErrorType errorType) {
        if (!condition) {
            throw new AppException(errorType);
        }
    }

    public static void throwIfNot(boolean condition, ErrorType errorType, HttpStatus httpStatus) {
        if (!condition) {
            throw new AppException(errorType, httpStatus);
        }
    }
}
