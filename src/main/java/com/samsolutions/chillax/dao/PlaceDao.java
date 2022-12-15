package com.samsolutions.chillax.dao;

import com.samsolutions.chillax.entity.Place;

import java.util.List;

/**
 * This interface declares methods of CRUD operations
 * to work with entities "Place" and transactions to the database.
 *
 * @see Place
 */
public interface PlaceDao
{
    /**
     * Creates new transmitted place in the database.
     *
     * @param place new place
     * @return id of the created place
     */
    int create(Place place);

    /**
     * Gets place from the database by primary key.
     *
     * @param key primary key
     * @return Place
     */
    Place getByPK(int key);

    /**
     * Updates place in the database.
     *
     * @param place modified place
     */
    void update(Place place);

    /**
     * Deletes place from the database by primary key.
     *
     * @param key primary key
     */
    void delete(int key);

    /**
     * Gets all places from the database.
     *
     * @return List<Place>
     */
    List<Place> getAll();
}