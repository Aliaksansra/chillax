package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.PlaceDTO;
import com.samsolutions.chillax.facades.PlaceFacade;
import com.samsolutions.chillax.validators.PlaceValidator;
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
 * This RestController works with places,
 * gets and sends them from and to the PlaceFacade.
 *
 * @see PlaceFacade
 */
@RestController
@RequestMapping("/api/admin/place")
public class PlacesController extends AbstractCRUDController<PlaceDTO>
{
    /**
     * Automatic injects of the PlaceFacade class that works with place entities and DTO.
     */
    @Autowired
    private PlaceFacade placeFacade;

    /**
     * Validates place data.
     */
    @Autowired
    private PlaceValidator placeValidator;

    /**
     * Automatic injects of the RestErrorHandler class
     * to handle MethodArgumentNotValidException
     * that can be thrown if there are PlaceDTO validation errors.
     *
     * @see RestErrorHandler
     */
    @Autowired
    private RestErrorHandler restErrorHandler;

    /**
     * Overrides method "add". Gets a new PlaceDTO, checks it for validation errors.
     * If there aren`t errors, the PlaceFacade will be called to create a new place in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param placeDTO PlaceDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> add(@Valid @RequestBody PlaceDTO placeDTO, Errors errors) {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            placeFacade.addNewPlace(placeDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Overrides method "delete". Gets place id and calls PlaceFacade to delete place in the database by id.
     * @param idPlace place id
     * @return HttpStatus.OK
     */
    @Override
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public HttpStatus delete(@PathVariable("id") int idPlace)
    {
        placeFacade.placeDelete(idPlace);
        return HttpStatus.OK;
    }

    /**
     * Overrides method "update". Gets a modified PlaceDTO, checks it for validation errors.
     * If there aren`t errors, the PlaceFacade will be called to update a modified place in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param placeDTO PlaceDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@Valid @RequestBody PlaceDTO placeDTO, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            placeFacade.placeUpdate(placeDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Overrides method "initBinder".
     * Initializes the WebDataBinder that binds PlaceValidator for place data validation.
     *
     * @see PlaceValidator
     * @param binder WebDataBinder
     */
    @Override
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setValidator(placeValidator);
    }
}