package com.samsolutions.chillax.entity;

import java.io.Serializable;
import java.util.List;

/**
 * This class describes entity "Roles"
 * according to fields of the table "ROLES" in the database.
 */
public class Roles implements Serializable
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
     * users who has this role
     *
     * @see Users
     */
    private List<Users> users;

    /**
     * empty constructor
     */
    public Roles()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idRole role id
     * @param role role name
     */
    public Roles(int idRole, String role)
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

    /**
     * Gets users list.
     *
     * @return users list
     */
    public List<Users> getUsers()
    {
        return users;
    }

    /**
     * Sets users list.
     *
     * @param users users list
     */
    public void setUsers(List<Users> users)
    {
        this.users = users;
    }

    /**
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Role: " + idRole + ", " + role;
    }
}