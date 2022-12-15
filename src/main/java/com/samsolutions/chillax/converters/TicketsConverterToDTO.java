package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.TicketsDTO;
import com.samsolutions.chillax.entity.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a tickets entities into a DTO.
 *
 * @see Converter
 * @see Tickets
 * @see TicketsDTO
 */
@Component
public class TicketsConverterToDTO implements Converter<Tickets, TicketsDTO>
{
    /**
     * Automatic injects of the EventsConverterToDTO class
     * to convert the Events entity into the EventsDTO required to create the TicketsDTO.
     *
     * @see EventsConverterToDTO
     */
    @Autowired
    private EventsConverterToDTO eventsConverterToDTO;

    /**
     * Automatic injects of the OrdersConverterToDTO class
     * to convert the Orders entity into the OrdersDTO required to create the TicketsDTO.
     *
     * @see OrdersConverterToDTO
     */
    @Autowired
    private OrdersConverterToDTO ordersConverterToDTO;

    /**
     * Overrides method 'convert', converts the @param Tickets
     * to the TicketsDTO and returns it.
     *
     * @param tickets Tickets
     * @return TicketsDTO
     */
    @Override
    public TicketsDTO convert(Tickets tickets)
    {
        TicketsDTO ticketsDTO = null;
        if (tickets != null)
        {
            ticketsDTO = new TicketsDTO(tickets.getIdTickets(), tickets.getGuid(), tickets.isUsed(), eventsConverterToDTO.convert(tickets.getEvent()), ordersConverterToDTO.convert(tickets.getOrder()));
        }
        return ticketsDTO;
    }
}