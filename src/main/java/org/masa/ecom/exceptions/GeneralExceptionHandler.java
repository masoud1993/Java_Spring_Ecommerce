package org.masa.ecom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

/**
 * Any exception that may occur in code, will handle by this class
 * We can create custome errors
 */
@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralException> handleValidationException(
            MethodArgumentNotValidException e
    ) {

        List<GeneralException.ExceptionPair> errors = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach(err -> {

            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();

            errors.add(
                    new GeneralException.ExceptionPair(fieldName, message)
            );
        });

        GeneralException response =
                new GeneralException(errors, e.getStatusCode().value());

        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralException> handleGeneralException(Exception e) {

        List<GeneralException.ExceptionPair> errors = new ArrayList<>();

        errors.add(
                new GeneralException.ExceptionPair(
                        "error",
                        e.getMessage()
                )
        );

        GeneralException response =
                new GeneralException(
                        errors,
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
