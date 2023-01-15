package br.com.martins.igor.backend.controllers.exceptions;

import br.com.martins.igor.backend.services.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError(Instant.now(), 404, "Resource not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(404).body(err);
    }
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e){
        ValidationError err = new ValidationError(400, "Validation error", Instant.now());
        for(FieldError error: e.getBindingResult().getFieldErrors()){
            err.addError(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(400).body(err);
    }
}
