package com.samsolutions.chillax.dto;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * This class describes fields
 * of cart.
 */
public class CartEntryDTO implements Serializable
{
    /**
     * event id
     */
    private int idEvent;

    /**
     * tickets number in cart
     */
    private int countOfTickets;

    /**
     * event title
     */
    private String title;

    /**
     * event date and time
     */
    private Timestamp datetimeOfEvent;

    /**
     * total price
     */
    private double price;

    /**
     * event place
     *
     * @see PlaceDTO
     */
    private PlaceDTO place;

    /**
     * empty constructor
     */
    public CartEntryDTO()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idEvent         event id
     * @param countOfTickets  tickets number
     * @param title           event title
     * @param datetimeOfEvent event date and time
     * @param price           total price
     * @param place           event place
     */
    public CartEntryDTO(int idEvent, int countOfTickets, String title, Timestamp datetimeOfEvent, double price, PlaceDTO place)
    {
        this.idEvent = idEvent;
        this.countOfTickets = countOfTickets;
        this.title = title;
        this.datetimeOfEvent = datetimeOfEvent;
        this.price = price;
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
     * Gets tickets number.
     *
     * @return tickets number
     */
    public int getCountOfTickets()
    {
        return countOfTickets;
    }

    /**
     * Sets tickets number.
     *
     * @param countOfTickets tickets number
     */
    public void setCountOfTickets(int countOfTickets)
    {
        this.countOfTickets = countOfTickets;
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
     * Gets total price.
     *
     * @return price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets total price.
     *
     * @param price total price
     */
    public void setPrice(double price)
    {
        this.price = price;
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