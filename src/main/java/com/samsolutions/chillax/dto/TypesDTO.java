package com.samsolutions.chillax.dto;

import java.io.Serializable;

/**
 * This class describes fields of DTO types.
 */
public class TypesDTO implements Serializable
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
     * empty constructor
     */
    public TypesDTO()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idType Type id
     * @param nameOfType Type name
     */
    public TypesDTO(int idType, String nameOfType)
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
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString(){
        return "idType: " + idType + ", nameOfType" + nameOfType;
    }
}