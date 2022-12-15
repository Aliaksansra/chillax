package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.TicketsDTO;
import com.samsolutions.chillax.entity.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a tickets entities from a DTO.
 *
 * @see Converter
 * @see Tickets
 * @see TicketsDTO
 */
@Component
public class TicketsConverterFromDTO implements Converter<TicketsDTO, Tickets>
{
    /**
     * Automatic injects of the EventsConverterFromDTO class
     * to convert the Events entity from the EventsDTO required to create the Tickets entity.
     *
     * @see EventsConverterFromDTO
     */
    @Autowired
    private EventsConverterFromDTO eventsConverterFromDTO;

    /**
     * Automatic injects of the OrdersConverterFromDTO class
     * to convert the Orders entity from the OrdersDTO required to create the Tickets entity.
     *
     * @see OrdersConverterFromDTO
     */
    @Autowired
    private OrdersConverterFromDTO ordersConverterFromDTO;

    /**
     * Overrides method 'convert', converts the @param TicketsDTO
     * to the Tickets entity and returns it.
     *
     * @param ticketsDTO TicketsDTO
     * @return Tickets
     */
    @Override
    public Tickets convert(TicketsDTO ticketsDTO)
    {
        Tickets tickets = null;
        if(ticketsDTO != null)
        {
            tickets = new Tickets(ticketsDTO.getIdTickets(),
                    ticketsDTO.getGuid(), ticketsDTO.isUsed(), eventsConverterFromDTO.convert(ticketsDTO.getEvent()), ordersConverterFromDTO.convert(ticketsDTO.getOrder()));
        }
        return tickets;
    }
}