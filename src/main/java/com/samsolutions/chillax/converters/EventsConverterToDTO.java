package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.entity.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming an events entities into a DTO.
 *
 * @see Converter
 * @see EventsDTO
 * @see Events
 */
@Component
public class EventsConverterToDTO implements Converter<Events, EventsDTO>
{
    /**
     * Automatic injects of the TypesConverterToDTO class
     * to convert the Types entity into the TypesDTO required to create the EventsDTO.
     *
     * @see TypesConverterToDTO
     */
    @Autowired
    private TypesConverterToDTO typesConverterToDTO;

    /**
     * Automatic injects of the PlaceConverterToDTO class
     * to convert the Place entity into the PlaceDTO required to create the EventsDTO.
     *
     * @see PlaceConverterToDTO
     */
    @Autowired
    private PlaceConverterToDTO placeConverterToDTO;

    /**
     * Overrides method 'convert', converts the @param Events
     * to the EventsDTO and returns it.
     *
     * @param event Events
     * @return EventsDTO
     */
    @Override
    public EventsDTO convert(Events event)
    {
        EventsDTO eventsDTO = null;
        if(event != null)
        {
            eventsDTO = new EventsDTO(event.getIdEvent(), event.getTitle(),
                    event.getDescription(),  event.getDatetimeOfEvent(),
                    event.getPrice(), event.getAllTickets(), event.getPoster(),typesConverterToDTO.convert(event.getType()), placeConverterToDTO.convert(event.getPlace()));
        }
        return eventsDTO;
    }
}