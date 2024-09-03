package com.workoutsheet.workoutsheet.exception;

import com.workoutsheet.workoutsheet.constants.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final ErrorType errorType;
    private final HttpStatus httpStatus;

    public AppException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public AppException(ErrorType errorType, HttpStatus httpStatus) {
        super(errorType.getMessage());
        this.errorType = errorType;
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

    public String getErrorCode() {
        return errorType.getCode();
    }

    public String getErrorMessage() {
        return errorType.getMessage();
    }
}
