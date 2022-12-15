package com.samsolutions.chillax.services;

import com.samsolutions.chillax.entity.Types;
import com.samsolutions.chillax.dao.TypesDao;

import java.util.List;

/**
 * This interface declares methods
 * for a service that works with Types DAO methods and entities.
 *
 * @see TypesDao
 * @see Types
 */
public interface TypesService
{
    /**
     * Gets all types
     *
     * @return List<Types>
     */
    List<Types> getTypes();

    /**
     * Creates a received type.
     *
     * @param type new type
     */
    void addType(Types type);

    /**
     * Deletes type by id.
     *
     * @param id types id
     */
    void deleteType(int id);

    /**
     * Updates type.
     *
     * @param type modified type
     */
    void updateType(Types type);

    /**
     * Gets type by id.
     * @param id type id
     * @return Types
     */
    Types getByPK(int id);
}