package com.samsolutions.chillax.facades;

import com.samsolutions.chillax.dto.TypesDTO;
import com.samsolutions.chillax.entity.Types;
import com.samsolutions.chillax.converters.TypesConverterFromDTO;
import com.samsolutions.chillax.converters.TypesConverterToDTO;
import com.samsolutions.chillax.services.TypesService;

import java.util.List;

/**
 * This interface implements a pattern Facade.
 * Declares methods to manage the service and converters
 * that work with Types and TypesDTO classes.
 *
 * @see TypesService
 * @see TypesConverterFromDTO
 * @see TypesConverterToDTO
 * @see TypesDTO
 * @see Types
 */
public interface TypesFacade
{
    /**
     * Gets all types.
     * Converts received entities to DTO and returns them.
     *
     * @return List<TypesDTO>
     */
    List<TypesDTO> getTypes();

    /**
     * Converts received DTO into entities and calls service for adding new type.
     *
     * @param typesDTO new type
     */
    void addNewType(TypesDTO typesDTO);

    /**
     * Calls service for deleting type by id.
     *
     * @param id Types id
     */
    void typeDelete(int id);

    /**
     * Converts received DTO into entities and calls service to update type.
     *
     * @param typesDTO modified type
     */
    void typeUpdate(TypesDTO typesDTO);
}