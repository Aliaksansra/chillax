package com.samsolutions.chillax.dto;

import java.io.Serializable;

/**
 * This class describes fields of DTO tickets.
 */
public class TicketsDTO implements Serializable
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
     * an event of this ticket
     *
     * @see EventsDTO
     */
    private EventsDTO event;

    /**
     * ticket order
     *
     * @see OrdersDTO
     */
    private OrdersDTO order;

    /**
     * empty constructor
     */
    public TicketsDTO()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idTickets ticket id
     * @param guid ticket guid
     * @param used ticket used
     * @param event ticket event
     * @param order ticket order
     */
    public TicketsDTO(int idTickets, String guid, boolean used, EventsDTO event, OrdersDTO order)
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
    public EventsDTO getEvent()
    {
        return event;
    }

    /**
     * Sets ticket event.
     *
     * @param event ticket event
     */
    public void setEvent(EventsDTO event)
    {
        this.event = event;
    }

    /**
     * Gets ticket order.
     *
     * @return ticket order
     */
    public OrdersDTO getOrder()
    {
        return order;
    }

    /**
     * Sets ticket order.
     *
     * @param order ticket order
     */
    public void setOrderDTO(OrdersDTO order)
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
        return "Ticket -  ID: " + idTickets + ", guid: " + guid + ", used: " + used;
    }
}




