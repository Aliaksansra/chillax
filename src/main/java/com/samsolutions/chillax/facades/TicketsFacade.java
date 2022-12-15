package com.samsolutions.chillax.facades;

import com.google.zxing.WriterException;
import com.samsolutions.chillax.dto.OrdersDTO;
import com.samsolutions.chillax.dto.TicketsDTO;
import com.samsolutions.chillax.converters.TicketsConverterFromDTO;
import com.samsolutions.chillax.converters.TicketsConverterToDTO;
import com.samsolutions.chillax.entity.Tickets;
import com.samsolutions.chillax.services.TicketsService;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * This interface implements a pattern Facade.
 * Declares methods to manage the service and converters
 * that work with Tickets and TicketsDTO classes.
 *
 * @see TicketsService
 * @see TicketsConverterToDTO
 * @see TicketsConverterFromDTO
 * @see TicketsDTO
 * @see Tickets
 */
public interface TicketsFacade
{
    /**
     * Сounts the number of remaining tickets to the
     * event specified by id
     *
     * @param idEvent Events id
     * @return number of Tickets
     */
    int getRemainderOfTickets(int idEvent);

    /**
     * Gets all tickets by order guid and user login.
     * Converts received entities to DTO and returns them.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return List<TicketsDTO>
     * @see OrdersDTO
     */
    List<TicketsDTO> getTicketsByOrder(String orderGuid, String login);

    /**
     * Gets tickets number by order guid, event id and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @param idEvent event id
     * @return count - number of Tickets
     * @see OrdersDTO
     */
    int getCountOfTickets(String orderGuid, int idEvent, String login);

    /**
     * Receives information about the ticket by guid and user login.
     * And encodes it in QRCode using Zxing library.
     *
     * @param login user login
     * @param guid ticket guid
     * @return BufferedImage
     * @throws WriterException WriterException
     */
    BufferedImage createQRCode(String guid, String login) throws WriterException;
}