package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.FieldErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles unexpected MethodArgumentNotValidException
 * that can be thrown if there are DTO validation errors.
 *
 * @see MethodArgumentNotValidException
 */
@ControllerAdvice
public class RestErrorHandler
{
    /**
     * source of validation error messages
     *
     * @see MessageSource
     */
    private MessageSource messageSource;

    /**
     * constructor
     *
     * @param messageSource MessageSource
     */
    @Autowired
    public RestErrorHandler(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }

    /**
     * Handles validation errors, sets error field and message to FieldErrorDTO class fields
     * and returns list of all FieldErrorDTO.
     *
     * @param errors validation errors
     * @return List<FieldErrorDTO>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<FieldErrorDTO> processValidationError(Errors errors)
    {
        List<FieldErrorDTO> listErrors = new ArrayList<>();
        for(FieldError fieldError : errors.getFieldErrors())
        {
            String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            listErrors.add(new FieldErrorDTO(fieldError.getField(), errorMessage));
        }
        return listErrors;
    }
}