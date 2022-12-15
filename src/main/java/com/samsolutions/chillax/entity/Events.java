package com.samsolutions.chillax.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * This class describes entity "Events"
 * according to fields of the table "EVENTS" in the database.
 */
public class Events implements Serializable
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
     *
     * @see Types
     */
    private Types type;

    /**
     * event location
     *
     * @see Place
     */
    private Place place;

    /**
     * comments on this event
     *
     * @see Comments
     */
    private List<Comments> comments;

    /**
     * tickets to this event
     *
     * @see Tickets
     */
    private List<Tickets> tickets;

    /**
     * empty constructor
     */
    public Events()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idEvent event id
     * @param title event title
     * @param description event description
     * @param poster event poster
     * @param datetimeOfEvent event date and time
     * @param price event price
     * @param allTickets event tickets number
     * @param type event type
     * @param place event place
     */
    public Events(int idEvent, String title, String description, String poster, Timestamp datetimeOfEvent, double price, int allTickets, Types type, Place place)
    {
        this.idEvent = idEvent;
        this.title = title;
        this.description = description;
        this.poster = poster;
        this.datetimeOfEvent = datetimeOfEvent;
        this.price = price;
        this.allTickets = allTickets;
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
    public Types getType()
    {
        return type;
    }

    /**
     * Sets event type.
     *
     * @param type event type
     */
    public void setType(Types type)
    {
        this.type = type;
    }

    /**
     * Gets event place.
     *
     * @return place
     */
    public Place getPlace()
    {
        return place;
    }

    /**
     * Sets event place.
     *
     * @param place event place
     */
    public void setPlace(Place place)
    {
        this.place = place;
    }

    /**
     * Gets comments list
     *
     * @return comments list
     */
    public List<Comments> getComments()
    {
        return comments;
    }

    /**
     * Sets comments list.
     *
     * @param comments comments list
     */
    public void setComments(List<Comments> comments)
    {
        this.comments = comments;
    }

    /**
     * Gets tickets list.
     *
     * @return tickets list
     */
    public List<Tickets> getTickets()
    {
        return tickets;
    }

    /**
     * Sets tickets list.
     *
     * @param tickets tickets list
     */
    public void setTickets(List<Tickets> tickets)
    {
        this.tickets = tickets;
    }

    /**
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Event: " + idEvent + ", " + title + ", " + description + ", " + poster + ", " + datetimeOfEvent + ", " + price;
    }
}