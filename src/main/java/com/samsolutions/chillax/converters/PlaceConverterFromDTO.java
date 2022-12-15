package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.PlaceDTO;
import com.samsolutions.chillax.entity.Place;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a place entities from a DTO.
 *
 * @see Converter
 * @see PlaceDTO
 * @see Place
 */
@Component
public class PlaceConverterFromDTO implements Converter<PlaceDTO, Place>
{
    /**
     * Overrides method 'convert', converts the @param PlaceDTO
     * to the Place entity and returns it.
     *
     * @param placeDTO PlaceDTO
     * @return Place
     */
    @Override
    public Place convert(PlaceDTO placeDTO)
    {
        Place place = null;
        if (placeDTO != null)
        {
            place = new Place(placeDTO.getIdPlace(), placeDTO.getCity(), placeDTO.getAddress(),
                    placeDTO.getPlaceName());
        }
        return place;
    }
}