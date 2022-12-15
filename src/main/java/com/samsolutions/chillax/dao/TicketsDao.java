package com.samsolutions.chillax.dao;

import com.samsolutions.chillax.entity.Tickets;
import java.util.List;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.entity.Orders;

/**
 * This interface declares methods of CRUD operations
 * to work with entities "Tickets" and transactions to the database.
 *
 * @see Tickets
 */
public interface TicketsDao
{
    /**
     * Creates new transmitted ticket in the database.
     *
     * @param tickets new ticket
     * @return id of the created ticket
     */
    int create(Tickets tickets);

    /**
     * Gets ticket from the database by primary key.
     *
     * @param key primary key
     * @return Tickets
     */
    Tickets getByPK(int key);

    /**
     * Updates ticket in the database.
     *
     * @param tickets modified ticket
     */
    void update(Tickets tickets);

    /**
     * Deletes ticket from the database by primary key.
     *
     * @param key primary key
     */
    void delete(int key);

    /**
     * Gets all tickets from the database.
     *
     * @return List<Tickets>
     */
    List<Tickets> getAll();

    /**
     * Gets tickets number by event id.
     *
     * @param idEvent event id
     * @return tickets number
     * @see Events
     */
    Long getNumberOfTicketsByEvent(int idEvent);

    /**
     * Gets all tickets from the database by order id and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return List<Tickets>
     * @see Orders
     */
    List<Tickets> getAllByOrder(String orderGuid, String login);

    /**
     * Gets tickets number by order id, event id and user login.
     *
     * @param orderGuid order guid
     * @param idEvent event id
     * @return tickets number
     * @see Orders
     * @see Events
     */
    int getCountTicketsByOrderAndEvent(String orderGuid, int idEvent, int idUser);

    /**
     * Gets ticket from the database by guid and user login.
     *
     * @param guid ticket guid
     * @param login user login
     * @return Tickets
     */
    Tickets getByGuid(String guid, String login);
}