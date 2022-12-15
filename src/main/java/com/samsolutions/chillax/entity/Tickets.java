package com.samsolutions.chillax.entity;

import java.io.Serializable;

/**
 * This class describes entity "Tickets"
 * according to fields of the table "TICKETS" in the database.
 */
public class Tickets implements Serializable
{
    /**
     * tickets id
     */
    private int idTickets;

    /**
     * ticket guid
     */
    private String guid;

    /**
     * ticket is used or not
     */
    private boolean used;

    /**
     * a event of this ticket
     *
     * @see Events
     */
    private Events event;

    /**
     * ticket order
     *
     * @see Orders
     */
    private Orders order;

    /**
     * empty constructor
     */
    public Tickets()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idTickets ticket id
     * @param guid ticket guid
     * @param used ticket is used or not
     * @param event ticket event
     * @param order ticket order
     */
    public Tickets(int idTickets, String guid, boolean used, Events event, Orders order)
    {
        this.idTickets = idTickets;
        this.guid = guid;
        this.used = used;
        this.event = event;
        this.order=order;
    }

    /**
     * Gets ticket id.
     *
     * @return id
     */
    public int getIdTickets()
    {
        return idTickets;
    }

    /**
     * Sets ticket id.
     *
     * @param idTickets ticket id
     */
    public void setIdTickets(int idTickets)
    {
        this.idTickets = idTickets;
    }

    /**
     * Gets ticket is used or not.
     *
     * @return used
     */
    public boolean isUsed()
    {
        return used;
    }

    /**
     * Sets ticket is used or not.
     *
     * @param used ticket used
     */
    public void setUsed(boolean used)
    {
        this.used = used;
    }

    /**
     * Gets ticket guid.
     *
     * @return guid
     */
    public String getGuid()
    {
        return guid;
    }

    /**
     * Sets ticket guid.
     *
     * @param guid ticket guid
     */
    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    /**
     * Gets ticket event.
     *
     * @return ticket event
     */
    public Events getEvent()
    {
        return event;
    }

    /**
     * Sets ticket event.
     *
     * @param event ticket event
     */
    public void setEvent(Events event)
    {
        this.event = event;
    }

    /**
     * Gets ticket order.
     *
     * @return ticket order
     */
    public Orders getOrder()
    {
        return order;
    }

    /**
     * Sets ticket order.
     *
     * @param order ticket order
     */
    public void setOrder(Orders order)
    {
        this.order = order;
    }

    /**
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Tickets: " + idTickets + ", " + guid + ", " + used;
    }
}