package com.samsolutions.chillax.dto;

import java.io.Serializable;

/**
 * This class describes fields of DTO place.
 */
public class PlaceDTO implements Serializable
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
     * empty constructor
     */
    public PlaceDTO()
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
    public PlaceDTO(int idPlace, String city, String address, String placeName)
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
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Place - ID: " + idPlace + ", " + placeName + ", " + city + ", " + address;
    }
}