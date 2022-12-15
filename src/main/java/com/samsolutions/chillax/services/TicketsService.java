package com.samsolutions.chillax.services;

import com.samsolutions.chillax.entity.Tickets;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.dao.TicketsDao;
import com.samsolutions.chillax.entity.Events;

import java.util.List;

/**
 * This interface declares methods
 * for a service that works with Tickets DAO methods and entities.
 *
 * @see TicketsDao
 * @see Tickets
 */
public interface TicketsService
{
    /**
     * Gets tickets number for the event by event id.
     *
     * @param idEvent event id
     * @return tickets number
     */
    int getTicketsNumberByEvent(int idEvent);

    /**
     * Gets tickets by order guid and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return List<Tickets>
     * @see Orders
     */
    List<Tickets> getTicketsByOrder(String orderGuid, String login);

    /**
     * Gets ticket by guid and user login.
     *
     * @param guid ticket guid
     * @param login user login
     * @return Tickets
     */
    Tickets getTicketByGuid(String guid, String login);

    /**
     * Gets tickets number by order guid, event id and user login.
     *
     * @param orderGuid order guid
     * @param idEvent event id
     * @param login user login
     * @return tickets number
     * @see Orders
     * @see Events
     */
    int getCountTickets(String orderGuid, int idEvent, String login);
}