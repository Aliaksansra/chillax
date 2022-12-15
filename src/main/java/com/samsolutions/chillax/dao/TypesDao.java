package com.samsolutions.chillax.dao;

import com.samsolutions.chillax.entity.Types;
import java.util.List;

/**
 * This interface declares methods of CRUD operations
 * to work with entities "Types" and transactions to the database.
 *
 * @see Types
 */
public interface TypesDao
{
    /**
     * Creates new transmitted type in the database.
     *
     * @param type new type
     * @return id of the created type
     */
    int create(Types type);

    /**
     * Gets type from the database by primary key.
     *
     * @param key primary key
     * @return Types
     */
    Types getByPK(int key);

    /**
     * Updates type in the database.
     *
     * @param type modified type
     */
    void update(Types type);

    /**
     * Deletes type from the database by primary key.
     *
     * @param key primary key
     */
    void delete(int key);

    /**
     * Gets all types from the database.
     *
     * @return List<Types>
     */
    List<Types> getAll();
}