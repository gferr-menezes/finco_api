package br.com.finco.finco_api.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RestControllerAdvice
public class RestExceptionHandler {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String errorDate = dateFormat.format(new Date());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionError> handleAllException(Exception ex) {
        String message = ex.getMessage();
        ExceptionError apiError = new ExceptionError(message == null ? "Internal server error" : message, HttpStatus.INTERNAL_SERVER_ERROR.value(), errorDate);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionError> unauthorizedException() {
        ExceptionError apiError = new ExceptionError("Unauthorized", HttpStatus.UNAUTHORIZED.value(), errorDate);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage().toLowerCase();
            errors.add(errorMessage);
        });

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        body.put("message", "Validation failed for object='userDto'. Error count: " + errors.size());
        body.put("errors", errors);

        return body;
    }

}
