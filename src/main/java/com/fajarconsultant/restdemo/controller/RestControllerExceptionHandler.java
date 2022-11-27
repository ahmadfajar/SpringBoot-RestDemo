package com.fajarconsultant.restdemo.controller;

import com.fajarconsultant.restdemo.domain.data.RestMessage;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

/**
 * Centralize global REST exception error handler.
 *
 * @author Ahmad Fajar &lt;ahmadfajar@gmail.com&gt;
 * @since 27/11/2022, modified: 27/11/2022 15:37
 */
@RestControllerAdvice
public class RestControllerExceptionHandler {
    private final MessageSource messageSource;

    public RestControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestMessage handleConstraintViolationException(
            ConstraintViolationException ex,
            Locale locale
    ) {
        List<String> messages = ex.getConstraintViolations()
                                  .stream()
                                  .map(x -> {
                                      String msg = messageSource.getMessage(
                                              x.getMessage(), null,
                                              ex.getMessage(), locale
                                      );
                                      return msg == null ? x.getMessage() : msg;
                                  })
                                  .toList();

        return new RestMessage(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                messages
        );
    }

    @ExceptionHandler(value = {
            EntityExistsException.class,
            EntityNotFoundException.class,
            org.hibernate.exception.ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestMessage handleEntityException(PersistenceException ex, Locale locale) {
        return new RestMessage(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), locale)
        );
    }

    @ExceptionHandler(value = {PersistenceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestMessage handlePersistenceException(PersistenceException ex, Locale locale) {
        return new RestMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), locale)
        );
    }
}