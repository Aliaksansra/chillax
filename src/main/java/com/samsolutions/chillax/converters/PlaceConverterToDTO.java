package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.PlaceDTO;
import com.samsolutions.chillax.entity.Place;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a place entities into a DTO.
 *
 * @see Converter
 * @see Place
 * @see PlaceDTO
 */
@Component
public class PlaceConverterToDTO implements Converter<Place, PlaceDTO>
{
    /**
     * Overrides method 'convert', converts the @param Place
     * to the PlaceDTO and returns it.
     *
     * @param place Place
     * @return PlaceDTO
     */
    @Override
    public PlaceDTO convert(Place place)
    {
        PlaceDTO placeDTO = null;
        if (place != null)
        {
            placeDTO = new PlaceDTO(place.getIdPlace(), place.getCity(), place.getAddress(),
                    place.getPlaceName());
        }
        return placeDTO;
    }
}