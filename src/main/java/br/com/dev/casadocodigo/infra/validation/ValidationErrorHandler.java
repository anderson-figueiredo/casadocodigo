package br.com.dev.casadocodigo.infra.validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();

        handleFieldErrors(bindingResult.getFieldErrors(), errors);
        handleGlobalErrors(bindingResult.getGlobalErrors(), errors);
        return errors;
    }

    private void handleGlobalErrors(List<ObjectError> globalErrors, Map<String, List<String>> errors) {
        globalErrors.forEach((error) -> {
            String fieldName = error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.computeIfAbsent(fieldName, k-> new ArrayList<>()).add(errorMessage);
        });
    }

    private void handleFieldErrors(List<FieldError> fieldErrors, Map<String, List<String>> errors) {
        fieldErrors.forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.computeIfAbsent(fieldName, k-> new ArrayList<>()).add(errorMessage);
        });
    }
}
