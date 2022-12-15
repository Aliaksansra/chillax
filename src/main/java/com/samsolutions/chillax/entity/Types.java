package com.samsolutions.chillax.entity;

import java.io.Serializable;
import java.util.List;

/**
 * This class describes entity "Types" of events
 * according to fields of the table "TYPES" in the database.
 */
public class Types implements Serializable
{
    /**
     * type id
     */
    private int idType;

    /**
     * type name
     */
    private String nameOfType;

    /**
     * events of this type
     *
     * @see Events
     */
    private List<Events> events;

    /**
     * empty constructor
     */
    public Types()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idType Type id
     * @param nameOfType Type name
     */
    public Types(int idType, String nameOfType)
    {
        this.idType = idType;
        this.nameOfType = nameOfType;
    }

    /**
     * Gets type id.
     *
     * @return id
     */
    public int getIdType()
    {
        return idType;
    }

    /**
     * Sets type id.
     *
     * @param idType type id
     */
    public void setIdType(int idType)
    {
        this.idType = idType;
    }

    /**
     * Gets type name.
     *
     * @return name
     */
    public String getNameOfType()
    {
        return nameOfType;
    }

    /**
     * Sets type name.
     *
     * @param nameOfType type name
     */
    public void setNameOfType(String nameOfType)
    {
        this.nameOfType = nameOfType;
    }

    /**
     * Gets events list.
     *
     * @return list of Events
     */
    public List<Events> getEvents()
    {
        return events;
    }

    /**
     * Sets list events.
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
        return "Type: " + idType + ", " + nameOfType;
    }
}