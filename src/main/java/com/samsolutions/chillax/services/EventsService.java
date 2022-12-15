package com.samsolutions.chillax.services;

import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.entity.Types;
import com.samsolutions.chillax.dao.EventsDao;

import java.util.List;

/**
 * This interface declares methods
 * for a service that works with Events DAO methods and entities.
 *
 * @see EventsDao
 * @see Events
 */
public interface EventsService {

    /**
     * Gets all events according to the specified page and pageSize.
     *
     * @param page page number
     * @param pageSize maximum number of elements per page
     * @return List<Events>
     */
    List<Events> getEvents(int page, int pageSize);

    /**
     * Gets all events by type id according to the specified page and pageSize.
     *
     * @param page page number
     * @param pageSize maximum number of elements per page
     * @param type - type of Event
     * @return List<Events>
     */
    List<Events> getEventsByType(int page, int pageSize, int type);


    /**
     * Gets event by id.
     *
     * @param id event id
     * @return Events
     */
    Events getEventByPK(int id);

    /**
     * Gets all events.
     *
     * @return List<Events>
     */
    List<Events> getAllEvents();

    /**
     * Gets all events by Types id.
     *
     * @param type type id
     * @return List<Events>
     * @see Types
     */
    List<Events> getAllEvents(int type);

    /**
     * Updates event.
     *
     * @param event modified event
     */
    void eventUpdate(Events event);

    /**
     * Deletes comment by id.
     *
     * @param id event id
     */
    void eventDelete(int id);

    /**
     * Creates a received event.
     *
     * @param event new event
     */
    void addEvent(Events event);

    /**
     * Gets all events by order id and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return List<Events>
     * @see Orders
     */
    List<Events> getOrderedEvents(String orderGuid, String login);

    /**
     * Checks poster name for uniqueness.
     *
     * @param posterName poster name
     * @return poster name is unique or not
     */
    boolean checkUniqueImage(String posterName);
}