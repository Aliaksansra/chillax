package com.samsolutions.chillax.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

/**
 * This abstract controller declares
 * the basic CRUD operations for heirs.
 */
public abstract class AbstractCRUDController<T>
{
    /**
     * Automatic injects of the RestErrorHandler class
     * to handle MethodArgumentNotValidException
     * that can be thrown if there are DTO validation errors.
     *
     * @see RestErrorHandler
     */
    @Autowired
    private RestErrorHandler restErrorHandler;

    /**
     * Gets a new object dto, checks it for validation errors.
     * If there aren`t errors, the facade class will be called to create a new object in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param dto T dto
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> if there are validation errors or
     * ResponseEntity<>(dto, HttpStatus.OK) if not.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> add(@Valid @RequestBody T dto, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    /**
     * Gets a modified object dto, checks it for validation errors.
     * If there aren`t errors, the facade class will be called to update an object in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param dto T dto
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> if there are validation errors
     * or ResponseEntity<>(dto, HttpStatus.OK) if not.
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@Valid @RequestBody T dto, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    /**
     * Gets dto id and calls facade class to delete an object in the database by id.
     *
     * @param id dto id
     * @return HttpStatus
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    HttpStatus delete(@PathVariable("id") int id)
    {
        return HttpStatus.OK;
    }

    /**
     * Initializes the WebDataBinder that binds from web request required parameters to T dto.
     *
     * @param binder WebDataBinder
     * @see WebDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
    }
}