package com.samsolutions.chillax.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * This class describes fields of DTO events.
 */
public class EventsDTO implements Serializable
{
    /**
     * event id
     */
    private int idEvent;

    /**
     * event title
     */
    private String title;

    /**
     * event description
     */
    private String description;

    /**
     * event poster name
     */
    private String poster;

    /**
     * event date and time
     */
    private Timestamp datetimeOfEvent;

    /**
     * price for one ticket to this event
     */
    private double price;

    /**
     * total number of tickets for event
     */
    private int allTickets;

    /**
     * event type
     */
    private TypesDTO type;

    /**
     * event location
     */
    private PlaceDTO place;

    /**
     * empty constructor
     */
    public EventsDTO()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idEvent event id
     * @param title event title
     * @param description event description
     * @param datetimeOfEvent event date and time
     * @param price event price
     * @param allTickets event tickets number
     * @param poster String
     * @param type event type
     * @param place event place
     */
    public EventsDTO(int idEvent, String title, String description, Timestamp datetimeOfEvent, double price, int allTickets, String poster, TypesDTO type, PlaceDTO place)
    {
        this.idEvent = idEvent;
        this.title = title;
        this.description = description;
        this.datetimeOfEvent = datetimeOfEvent;
        this.price = price;
        this.allTickets = allTickets;
        this.poster = poster;
        this.type = type;
        this.place = place;
    }

    /**
     * Gets event id.
     *
     * @return id
     */
    public int getIdEvent()
    {
        return idEvent;
    }

    /**
     * Sets event id.
     *
     * @param idEvent event id
     */
    public void setIdEvent(int idEvent)
    {
        this.idEvent = idEvent;
    }

    /**
     * Gets event title.
     *
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets event title.
     *
     * @param title event title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Gets event description.
     *
     * @return event description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets event description.
     *
     * @param description event description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Gets event poster.
     *
     * @return poster
     */
    public String getPoster()
    {
        return poster;
    }

    /**
     * Sets event poster.
     *
     * @param poster event poster
     */
    public void setPoster(String poster)
    {
        this.poster = poster;
    }

    /**
     * Gets event date and time.
     *
     * @return date and time
     */
    public Timestamp getDatetimeOfEvent()
    {
        return datetimeOfEvent;
    }

    /**
     * Sets event date and time.
     *
     * @param datetimeOfEvent event date and time
     */
    public void setDatetimeOfEvent(Timestamp datetimeOfEvent)
    {
        this.datetimeOfEvent = datetimeOfEvent;
    }

    /**
     * Gets event price.
     *
     * @return price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets event price.
     *
     * @param price event price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Gets event tickets number.
     *
     * @return tickets number
     */
    public int getAllTickets()
    {
        return allTickets;
    }

    /**
     * Sets event tickets number.
     *
     * @param allTickets event tickets number
     */
    public void setAllTickets(int allTickets)
    {
        this.allTickets = allTickets;
    }

    /**
     * Gets event type.
     *
     * @return type
     */
    public TypesDTO getType()
    {
        return type;
    }

    /**
     * Sets event type.
     *
     * @param type event type
     */
    public void setType(TypesDTO type)
    {
        this.type = type;
    }

    /**
     * Gets event place.
     *
     * @return place
     */
    public PlaceDTO getPlace()
    {
        return place;
    }

    /**
     * Sets event place.
     *
     * @param place event place
     */
    public void setPlace(PlaceDTO place)
    {
        this.place = place;
    }
}