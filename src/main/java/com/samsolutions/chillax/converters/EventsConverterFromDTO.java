package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.entity.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming an events entities from a DTO.
 *
 * @see Converter
 * @see EventsDTO
 * @see Events
 */
@Component
public class EventsConverterFromDTO implements Converter<EventsDTO, Events>
{
    /**
     * Automatic injects of the TypesConverterFromDTO class
     * to convert the Types entity from the TypesDTO required to create the Events entity.
     *
     * @see TypesConverterFromDTO
     */
    @Autowired
    private TypesConverterFromDTO typesConverterFromDTO;

    /**
     * Automatic injects of the PlaceConverterFromDTO class
     * to convert the Place entity from the PlaceDTO required to create the Events entity.
     *
     * @see PlaceConverterFromDTO
     */
    @Autowired
    private PlaceConverterFromDTO placeConverterFromDTO;

    /**
     * Overrides method 'convert', converts the @param EventsDTO
     * to the Events entity and returns it.
     *
     * @param eventsDTO EventsDTO
     * @return Events
     */
    @Override
    public Events convert(EventsDTO eventsDTO)
    {
        Events events = null;
        if(eventsDTO != null)
        {
            events = new Events(eventsDTO.getIdEvent(), eventsDTO.getTitle(),
                    eventsDTO.getDescription(), eventsDTO.getPoster(),
                    eventsDTO.getDatetimeOfEvent(), eventsDTO.getPrice(),
                    eventsDTO.getAllTickets(), typesConverterFromDTO.convert(eventsDTO.getType()), placeConverterFromDTO.convert(eventsDTO.getPlace()));
        }
        return events;
    }
}