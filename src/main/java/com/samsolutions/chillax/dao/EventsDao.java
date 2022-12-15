package com.samsolutions.chillax.dao;

import com.samsolutions.chillax.entity.Events;
import java.util.List;
import com.samsolutions.chillax.entity.Types;

/**
 * This interface declares methods of CRUD operations
 * to work with entities "Events" and transactions to the database.
 *
 * @see Events
 */
public interface EventsDao
{
    /**
     * Creates new transmitted event in the database.
     *
     * @param event new event
     * @return event id
     */
    int create(Events event);

    /**
     * Get one event from the database by primary key.
     *
     * @param key primary key
     * @return Events
     */
    Events getByPK(int key);

    /**
     * Updates event in the database.
     *
     * @param event modified event
     */
    void update(Events event);

    /**
     * Deletes event from the database by primary key.
     *
     * @param key primary key
     */
    void delete(int key);

    /**
     * Gets all events from the database by type id
     * according to the specified page and in the specified amount(@param pageSize).
     *
     * @param page page number
     * @param pageSize maximum number of elements per page
     * @param type type id
     * @return List<Events>
     * @see Types
     */
    List<Events> getAllByTypeForPage(int page, int pageSize, int type);

    /**
     * Gets all events from the database
     * according to the specified page and in the specified amount(@param pageSize).
     *
     * @param page page number
     * @param pageSize maximum number of elements per page
     * @return List<Events>
     */
    List<Events> getAllForPage(int page, int pageSize);

    /**
     * Gets all events from the database.
     *
     * @return List<Events>
     */
    List<Events> getAll();

    /**
     * Gets  all events from the database by type id.
     *
     * @param type type id
     * @return List<Events>
     */
    List<Events> getAllByType(int type);

    /**
     * Gets all events from the database by order guid and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return List<Events>
     */
    List<Events> getOrderedEvents(String orderGuid, String login);

    /**
     * Checks poster name for uniqueness in the database.
     *
     * @return true - if poster name is unique, false - if not
     */
    boolean isUniquePosterName(String posterName);
}