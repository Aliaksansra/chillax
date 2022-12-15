package com.samsolutions.chillax.facades.impl;

import com.google.zxing.WriterException;
import com.samsolutions.chillax.converters.TicketsConverterToDTO;
import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.dto.OrdersDTO;
import com.samsolutions.chillax.dto.PlaceDTO;
import com.samsolutions.chillax.dto.TicketsDTO;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.entity.Place;
import com.samsolutions.chillax.entity.Tickets;
import com.samsolutions.chillax.services.TicketsService;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class TicketsFacadeImplTest
{

    @Mock
    private TicketsService ticketsService;

    @Mock
    private TicketsConverterToDTO converterToDTO;

    @InjectMocks
    private TicketsFacadeImpl ticketsFacade;

    private static List<Tickets> ticketsList = new ArrayList<>();

    private static List<TicketsDTO> ticketsListDTO = new ArrayList<>();

    private static final Logger logger = getLogger(CartFacadeImplTest.class);

    @BeforeAll
    public static void setUp()
    {
        ticketsList.add(new Tickets(1, "c94f17f6-ac38-4c37-b1b5-3a6f7075d927",false, new Events(1, "Динамо-Минск - СКА", "Хоккейный матч Динамо-Минск - СКА", "cka.jpg", Timestamp.valueOf("2019-01-04 19:30:00.000"), 7.00, 15000, null, new Place(1, "Минск", "ул.Октябрьская 5", "Концертный зал Минск")), new Orders(1, "af2d7a1b-cb19-4736-b429-5cb73f5e672e", 50, 0, Date.valueOf("2018-11-11"), true, null)));
        ticketsList.add(new Tickets(2, "1a0d4222-ad4f-45e3-aa90-fb7475784486",false, null, null));
        ticketsListDTO.add(new TicketsDTO(1, "c94f17f6-ac38-4c37-b1b5-3a6f7075d927",false, new EventsDTO(1, "Динамо-Минск - СКА", "Хоккейный матч Динамо-Минск - СКА", Timestamp.valueOf("2019-01-04 19:30:00.000"), 7.00, 15000, "cka.jpg", null, new PlaceDTO(1, "Минск", "ул.Октябрьская 5", "Концертный зал Минск")), new OrdersDTO(1, "af2d7a1b-cb19-4736-b429-5cb73f5e672e", 50, 0, Date.valueOf("2018-11-11"), true, 0)));
        ticketsListDTO.add(new TicketsDTO(2, "1a0d4222-ad4f-45e3-aa90-fb7475784486",false, null, null));
    }

    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRemainderOfTickets()
    {
        when(ticketsService.getTicketsNumberByEvent(1)).thenReturn(10);
        int result = ticketsFacade.getRemainderOfTickets(1);
        assertTrue(result != 0);
        assertEquals(10, result);
    }

    @Test
    public void getTicketsByOrder()
    {
        when(ticketsService.getTicketsByOrder("right order", "right login")).thenReturn(ticketsList);
        when(converterToDTO.convert(ticketsList.get(0))).thenReturn(ticketsListDTO.get(0));
        when(converterToDTO.convert(ticketsList.get(1))).thenReturn(ticketsListDTO.get(1));
        List<TicketsDTO> actList = ticketsFacade.getTicketsByOrder("right order", "right login");
        assertEquals(ticketsListDTO, actList);
        assertNotNull(actList);
    }

    @Test
    public void getTicketsByWrongOrder()
    {
        when(ticketsService.getTicketsByOrder("right order", "right login")).thenReturn(ticketsList);
        when(converterToDTO.convert(ticketsList.get(0))).thenReturn(ticketsListDTO.get(0));
        when(converterToDTO.convert(ticketsList.get(1))).thenReturn(ticketsListDTO.get(1));
        List<TicketsDTO> actList = ticketsFacade.getTicketsByOrder("wrong order", "right login");
        assertNotEquals(ticketsListDTO, actList);
        assertNull(actList);
    }

    @Test
    public void getTicketsByOrderToWrongUser()
    {
        when(ticketsService.getTicketsByOrder("right order", "right login")).thenReturn(ticketsList);
        when(converterToDTO.convert(ticketsList.get(0))).thenReturn(ticketsListDTO.get(0));
        when(converterToDTO.convert(ticketsList.get(1))).thenReturn(ticketsListDTO.get(1));
        List<TicketsDTO> actList = ticketsFacade.getTicketsByOrder("right order", "wrong login");
        assertNotEquals(ticketsListDTO, actList);
        assertNull(actList);
    }

    @Test
    public void getCountOfTickets()
    {
        when(ticketsService.getCountTickets("some guid", 1, "admin")).thenReturn(10);
        int result = ticketsFacade.getCountOfTickets("some guid", 1, "admin");
        assertTrue(result != 0);
        assertEquals(10, result);
    }

    @Test
    public void getCountOfTicketsToWrongUser()
    {
        when(ticketsService.getCountTickets("some guid", 1, "admin")).thenReturn(10);
        int result = ticketsFacade.getCountOfTickets("some guid", 1, "wrong user");
        assertTrue(result == 0);
    }

    @Test
    public void createQRCode()throws WriterException
    {
        Tickets ticket = ticketsList.get(0);
        when(ticketsService.getTicketByGuid("some guid", "admin")).thenReturn(ticket);
        when(converterToDTO.convert(ticket)).thenReturn(ticketsListDTO.get(0));
        BufferedImage image = ticketsFacade.createQRCode("some guid", "admin");
        assertNotNull(image);
    }

    @Test
    public void createQRCodeToWrongUser()
    {
        try
        {
            Tickets ticket = ticketsList.get(0);
            when(ticketsService.getTicketByGuid("some guid", "admin")).thenReturn(ticket);
            when(converterToDTO.convert(ticket)).thenReturn(ticketsListDTO.get(0));
            BufferedImage image = ticketsFacade.createQRCode("some guid", "wrong user");
            assertNull(image);
        }catch (WriterException exp)
        {
            logger.error(exp);
        }
    }
}