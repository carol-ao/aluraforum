package com.carol.forum.config;

import com.carol.forum.exceptions.ValidationError;
import com.carol.forum.exceptions.ResourceNotFoundException;
import com.carol.forum.exceptions.StandardError;
import com.carol.forum.exceptions.ValidationFieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @Autowired
    MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationErrorHandler(MethodArgumentNotValidException exception,  HttpServletRequest httpServletRequest) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ValidationFieldError> validationFieldErrors = fieldErrors.stream().map(fieldError ->
        {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            return new ValidationFieldError(fieldError.getField(), message);
        }).collect(Collectors.toList());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .fieldErrors(validationFieldErrors)
                .path(httpServletRequest.getRequestURI()).build());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> exceptionHandler(Exception e, HttpServletRequest httpServletRequest){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .path(httpServletRequest.getRequestURI()).build());
    }
}
