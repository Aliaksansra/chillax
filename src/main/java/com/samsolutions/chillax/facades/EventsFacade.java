package com.samsolutions.chillax.facades;

import com.samsolutions.chillax.converters.EventsConverterFromDTO;
import com.samsolutions.chillax.converters.EventsConverterToDTO;
import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.services.EventsService;
import com.samsolutions.chillax.dto.TypesDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This interface implements a pattern Facade.
 * Declares methods to manage the service and converters
 * that work with Events and EventsDTO classes.
 *
 * @see EventsService
 * @see EventsConverterToDTO
 * @see EventsConverterFromDTO
 * @see EventsDTO
 * @see Events
 */
public interface EventsFacade
{
    /**
     * Gets all events according to the specified page and pageSize due to service.
     * Converts received entities to DTO and returns them.
     *
     * @param page page number
     * @param pageSize maximum number of elements per page
     * @return List<EventsDTO>
     */
    List<EventsDTO> getEventsDTO(int page, int pageSize);

    /**
     * Gets event DTO by id.
     * Converts received from service entity to DTO and returns it.
     *
     * @param id event id
     * @return EventsDTO
     */
    EventsDTO getEventByPK(int id);

    /**
     * Gets all events.
     * Converts received entities to DTO and returns them.
     *
     * @return List<EventsDTO>
     */
    List<EventsDTO> getAllEvents();

    /**
     * Gets all events according to the specified type, page and pageSize due to service.
     * Converts received entities to DTO and returns them.
     *
     * @param page page number
     * @param pageSize maximum number of elements per page
     * @param type type id
     * @return List<EventsDTO>
     * @see TypesDTO
     */
    List<EventsDTO> getEventsDTOByType(int page, int pageSize, int type);

    /**
     * Gets all events by Type.
     * Converts received entities to DTO and returns them.
     *
     * @param type type id
     * @return List<EventsDTO>
     * @see TypesDTO
     */
    List<EventsDTO> getAllEventsByType(int type);

    /**
     * Converts received EventsDTO into entities and calls service for updating event.
     *
     * @param eventsDTO modified event
     */
    void eventUpdate(EventsDTO eventsDTO);

    /**
     * Calls service for deleting event by id.
     *
     * @param id event id
     */
    void eventDelete(int id);

    /**
     * Converts received EventsDTO into entities and calls service for adding new event.
     *
     * @param eventsDTO new event
     */
    void addNewEvent(EventsDTO eventsDTO);

    /**
     * Calls service for getting event by id.
     *
     * @param id event id
     * @return Events
     */
    Events getByPK(int id);

    /**
     ** Gets all events by Orders guid and user login.
     *  Converts received entities to DTO and returns them.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return List<EventsDTO>
     */
    List<EventsDTO> getOrderedEvents(String orderGuid, String login);

    /**
     * If poster name is unique new event poster will be loaded in directory with other event images.
     *
     * @param image MultipartFile
     * @param request HttpServletRequest
     * @return boolean poster name is unique or not
     */
    boolean loadImage(MultipartFile image, HttpServletRequest request);
}