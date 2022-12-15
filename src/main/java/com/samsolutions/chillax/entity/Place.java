package com.samsolutions.chillax.entity;

import java.io.Serializable;
import java.util.List;

/**
 * This class describes entity "Place"
 * according to fields of the table "PLACE" in the database.
 */
public class Place implements Serializable
{
    /**
     * place id
     */
    private int idPlace;

    /**
     * The city where this place is located.
     */
    private String city;

    /**
     * place address
     */
    private String address;

    /**
     * place name
     */
    private String placeName;

    /**
     * Events that are in this place.
     *
     * @see Events
     */
    private List<Events> events;

    /**
     * empty constructor
     */
    public Place()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idPlace place id
     * @param city place city
     * @param address place address
     * @param placeName place name
     */
    public Place(int idPlace, String city, String address, String placeName)
    {
        this.idPlace = idPlace;
        this.city = city;
        this.address = address;
        this.placeName = placeName;
    }

    /**
     * Gets place id.
     *
     * @return place id
     */
    public int getIdPlace()
    {
        return idPlace;
    }

    /**
     * Sets place id.
     *
     * @param idPlace place id
     */
    public void setIdPlace(int idPlace)
    {
        this.idPlace = idPlace;
    }

    /**
     * Gets place name.
     *
     * @return name
     */
    public String getPlaceName()
    {
        return placeName;
    }

    /**
     * Sets place name.
     *
     * @param placeName place name
     */
    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }

    /**
     * Gets place city.
     *
     * @return city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets place city.
     *
     * @param city place city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Gets place address.
     *
     * @return address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * Sets place address.
     *
     * @param address place address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Gets events list.
     *
     * @return events list
     */
    public List<Events> getEvents()
    {
        return events;
    }

    /**
     * Sets events list.
     *
     * @param events events list
     */
    public void setEvents(List<Events> events)
    {
        this.events = events;
    }

    /**
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Place: " + idPlace + ", " + city + ", " + address + ", " + placeName;
    }
}
