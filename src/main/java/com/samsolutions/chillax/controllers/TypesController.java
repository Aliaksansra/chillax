package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.TypesDTO;
import com.samsolutions.chillax.facades.TypesFacade;
import com.samsolutions.chillax.validators.TypeValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

/**
 * This RestController works with types,
 * gets and sends them from and to the TypesFacade.
 *
 * @see TypesFacade
 */
@RestController
@RequestMapping("/api/admin/type")
public class TypesController extends AbstractCRUDController<TypesDTO>
{
    /**
     * Automatic injects of the TypesFacade class that works with type entities and DTO.
     */
    @Autowired
    private TypesFacade typesFacade;

    /**
     * Validates type data.
     */
    @Autowired
    private TypeValidator typeValidator;

    /**
     * Automatic injects of the RestErrorHandler class
     * to handle MethodArgumentNotValidException
     * that can be thrown if there are TypesDTO validation errors.
     *
     * @see RestErrorHandler
     */
    @Autowired
    private RestErrorHandler restErrorHandler;

    /**
     * Overrides method "add". Gets a new TypesDTO, checks it for validation errors.
     * If there aren`t errors, the TypesFacade will be called to create a new type in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param typesDTO TypesDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
    public @ResponseBody
            ResponseEntity<?> add(@Valid @RequestBody TypesDTO typesDTO, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            typesFacade.addNewType(typesDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Overrides method "delete". Gets type id and calls TypesFacade to delete type in the database by id.
     *
     * @param idType type id
     * @return HttpStatus.OK
     */
    @Override
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public HttpStatus delete(@PathVariable("id") int idType)
    {
        typesFacade.typeDelete(idType);
        return HttpStatus.OK;
    }

    /**
     * Overrides method "update". Gets a modified TypesDTO, checks it for validation errors.
     * If there aren`t errors, the TypesFacade will be called to update a modified type in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param type TypesDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@Valid @RequestBody TypesDTO type, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            typesFacade.typeUpdate(type);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Overrides method "initBinder".
     * Initializes the WebDataBinder that binds TypeValidator for type data validation.
     *
     * @see TypeValidator
     * @param binder WebDataBinder
     */
    @Override
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setValidator(typeValidator);
    }
}