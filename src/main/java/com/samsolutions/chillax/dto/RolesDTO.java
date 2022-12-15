package com.samsolutions.chillax.dto;

import java.io.Serializable;

/**
 * This class describes fields of DTO roles.
 */
public class RolesDTO implements Serializable
{
    /**
     * role id
     */
    private int idRole;

    /**
     * role name
     */
    private String role;

    /**
     * empty constructor
     */
    public RolesDTO()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idRole role id
     * @param role role name
     */
    public RolesDTO(int idRole, String role)
    {
        this.idRole = idRole;
        this.role = role;
    }

    /**
     * Gets role id.
     *
     * @return role id
     */
    public int getIdRole()
    {
        return idRole;
    }

    /**
     * Sets role id.
     *
     * @param idRole role id
     */
    public void setIdRole(int idRole)
    {
        this.idRole = idRole;
    }

    /**
     * Gets role name.
     *
     * @return role name
     */
    public String getRole()
    {
        return role;
    }

    /**
     * Sets role name.
     *
     * @param role role name
     */
    public void setRole(String role)
    {
        this.role = role;
    }
}
