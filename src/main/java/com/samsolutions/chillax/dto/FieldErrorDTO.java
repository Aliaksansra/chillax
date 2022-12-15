package com.samsolutions.chillax.dto;

import java.io.Serializable;

/**
 * This class describes fields of validate error.
 */
public class FieldErrorDTO implements Serializable
{
    /**
     * error field
     */
    private String field;

    /**
     * error message
     */
    private String message;

    /**
     * constructor with parameters
     *
     * @param field   error field
     * @param message error message
     */
    public FieldErrorDTO(String field, String message)
    {
        this.field = field;
        this.message = message;
    }

    /**
     * empty constructor
     */
    public FieldErrorDTO()
    {
    }

    /**
     * Gets error field.
     *
     * @return field
     */
    public String getField()
    {
        return field;
    }

    /**
     * Sets error field.
     *
     * @param field error field
     */
    public void setField(String field)
    {
        this.field = field;
    }

    /**
     * Gets error message.
     *
     * @return message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Sets error message
     *
     * @param message error message
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
}