package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.facades.EventsFacade;
import com.samsolutions.chillax.validators.EventValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.util.Iterator;

/**
 * This RestController works with events,
 * gets and sends them from and to the EventsFacade.
 *
 * @see EventsFacade
 */
@RestController
@RequestMapping("/api/admin/event/")
public class EventsController extends AbstractCRUDController<EventsDTO>
{
    /**
     * Automatic injects of the EventsFacade class that works with event entities and DTO.
     */
    @Autowired
    private EventsFacade eventsFacade;

    /**
     * Automatic injects of the RestErrorHandler class
     * to handle MethodArgumentNotValidException
     * that can be thrown if there are EventsDTO validation errors.
     *
     * @see RestErrorHandler
     */
    @Autowired
    private RestErrorHandler restErrorHandler;

    /**
     * Validates event data.
     */
    @Autowired
    private EventValidator eventValidator;

    /**
     * Overrides method "add". Gets a new EventsDTO, checks it for validation errors.
     * If there aren`t errors calls EventsFacade to create a new event in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param eventsDTO EventsDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> add(@Valid @RequestBody EventsDTO eventsDTO, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
        {
            eventsFacade.addNewEvent(eventsDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Loads new event poster using MultipartHttpServletRequest.
     * Calls EventsFacade to save it in directory with other event images if this poster is unique.
     *
     * @param request MultipartHttpServletRequest
     * @param httpServletRequest HttpServletRequest
     * @return HttpStatus.OK - if poster is unique or HttpStatus.ALREADY_REPORTED - if not
     */
    @RequestMapping(method = RequestMethod.POST, value = "/loadimage", produces = "application/json")
    public @ResponseBody
    HttpStatus loadImage (MultipartHttpServletRequest request, HttpServletRequest httpServletRequest)
    {
        Iterator<String> itr =  request.getFileNames();
        boolean posterIsUnique = eventsFacade.loadImage(request.getFile(itr.next()), httpServletRequest);
        if(posterIsUnique){
            return HttpStatus.OK;
        } else {
            return HttpStatus.ALREADY_REPORTED;
        }
    }

    /**
     * Overrides method "update". Gets a modified EventsDTO, checks it for validation errors.
     * If there aren`t errors calls EventsFacade to update event in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param eventsDTO EventsDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@Valid @RequestBody EventsDTO eventsDTO, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
        {
            eventsFacade.eventUpdate(eventsDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Overrides method "delete". Gets event id and calls EventsFacade
     * to delete event in the database by id.
     *
     * @param idEvent event id
     * @return HttpStatus.OK
     */
    @Override
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public HttpStatus delete(@PathVariable("id") int idEvent)
    {
        eventsFacade.eventDelete(idEvent);
        return HttpStatus.OK;
    }

    /**
     * Overrides method "initBinder".
     * Initializes the WebDataBinder that binds EventValidator for event data validation
     * and Timestamp class to format received date and time values.
     *
     * @see EventValidator
     * @param binder WebDataBinder
     */
    @Override
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setValidator(eventValidator);
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport()
        {
            public void setAsText(String value)
            {
                if (StringUtils.countMatches(value, ":") == 1)
                {
                    value += ":00";
                }
                setValue(Timestamp.valueOf(value.replace("T", " ")));
            }
        });
    }
}