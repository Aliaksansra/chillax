package com.samsolutions.chillax.facades;

import com.samsolutions.chillax.converters.PlaceConverterFromDTO;
import com.samsolutions.chillax.converters.PlaceConverterToDTO;
import com.samsolutions.chillax.dto.PlaceDTO;
import com.samsolutions.chillax.entity.Place;
import com.samsolutions.chillax.services.PlaceService;

import java.util.List;

/**
 * This interface implements a pattern Facade.
 * Declares methods to manage the service and converters
 * that work with Place and PlaceDTO classes.
 *
 * @see PlaceService
 * @see PlaceConverterToDTO
 * @see PlaceConverterFromDTO
 * @see PlaceDTO
 * @see Place
 */
public interface PlaceFacade
{
    /**
     * Gets all places.
     * Converts received entities to DTO and returns them.
     *
     * @return  List<PlaceDTO>
     */
    List<PlaceDTO> getPlaces();

    /**
     * Converts received DTO into entities and calls service to create new place.
     *
     * @param place new place
     */
    void addNewPlace(PlaceDTO place);

    /**
     * Converts received DTO into entities and calls service to update place.
     *
     * @param placeDTO modified place
     */
    void placeUpdate(PlaceDTO placeDTO);

    /**
     * Calls service to delete place by id.
     *
     * @param id Place id
     */
    void placeDelete(int id);
}