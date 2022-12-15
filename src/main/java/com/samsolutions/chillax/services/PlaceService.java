package com.samsolutions.chillax.services;

import com.samsolutions.chillax.entity.Place;
import com.samsolutions.chillax.dao.PlaceDao;
import java.util.List;

/**
 * This interface declares methods
 * for a service that works with Place DAO methods and entities.
 *
 * @see PlaceDao
 * @see Place
 */
public interface PlaceService
{
    /**
     * Gets all places.
     *
     * @return List<Place>
     */
    List<Place> getPlaces();

    /**
     * Creates a received place
     *
     * @param place new place
     */
    void addPlace(Place place);

    /**
     * Updates place.
     *
     * @param place modified place
     */
    void updatePlace(Place place);

    /**
     * Deletes place by id.
     *
     * @param id place id
     */
    void deletePlace(int id);

    /**
     * Gets place by id.
     *
     * @param id place id
     * @return Place
     */
    Place getByPK(int id);
}