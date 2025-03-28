package com.workoutsheet.workoutsheet.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex, HttpServletRequest request) {
        logError(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getErrorCode(),
                ex.getErrorMessage(),
                null,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errorList = new ArrayList<>();
        for (var error : ex.getBindingResult().getAllErrors()) {
            String errorMessage = error.getDefaultMessage();
            errorList.add(errorMessage);
        }
        Map<String, Object> details = new HashMap<>();
        details.put("errors", errorList);

        ErrorResponse errorResponse = new ErrorResponse(
                "VALIDATION_ERROR",
                "Existem erros de validação nos campos enviados.",
                details,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<String> errorList = new ArrayList<>();
        ex.getConstraintViolations().forEach(violation -> errorList.add(violation.getMessage()));
        Map<String, Object> details = new HashMap<>();
        details.put("errors", errorList);

        ErrorResponse errorResponse = new ErrorResponse(
                "VALIDATION_ERROR",
                "Existem erros de validação nos campos enviados.",
                details,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "ARGUMENT_TYPE_MISMATCH",
                "Um dos parâmetros informados está com um formato inválido.",
                null,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "NOT_FOUND",
                "O recurso solicitado não foi encontrado.",
                null,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "METHOD_NOT_SUPPORTED",
                "A operação solicitada não é permitida para este recurso.",
                null,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "MALFORMED_JSON_REQUEST",
                "O corpo da requisição está mal formatado ou é inválido.",
                null,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        logError(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                null,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logError(Exception ex) {
        logger.error("Exception occurred: ", ex);
    }

    public record ErrorResponse(
            String code,
            String message,
            Map<String, Object> details,
            String path,
            LocalDateTime timestamp
    ) {
        public ErrorResponse(String code, String message) {
            this(code, message, null, null, LocalDateTime.now());
        }

        public ErrorResponse(String code, String message, Map<String, Object> details) {
            this(code, message, details, null, LocalDateTime.now());
        }

        public ErrorResponse(String code, String message, Map<String, Object> details, String path) {
            this(code, message, details, path, LocalDateTime.now());
        }
    }
}
