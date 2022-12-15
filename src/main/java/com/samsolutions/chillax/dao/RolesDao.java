package com.samsolutions.chillax.dao;

import com.samsolutions.chillax.entity.Roles;
import java.util.List;

/**
 * This interface declares methods of CRUD operations
 * to work with entities "Roles" and transactions to the database.
 *
 * @see Roles
 */
public interface RolesDao
{
    /**
     * Creates new transmitted role in the database.
     *
     * @param role new role
     * @return id of the created role
     */
    int create(Roles role);

    /**
     * Gets role from the database by primary key.
     *
     * @param key primary key
     * @return Roles
     */
    Roles getByPK(int key);

    /**
     * Updates role in the database.
     *
     * @param role modified role
     */
    void update(Roles role);

    /**
     * Deletes role from the database by primary key.
     *
     * @param key primary key
     */
    void delete(int key);

    /**
     * Gets all roles from the database.
     *
     * @return List<Roles>
     */
    List<Roles> getAll();

    /**
     * Gets role from the database by role name.
     *
     * @param name role name
     * @return Roles
     */
    Roles getByName(String name);
}