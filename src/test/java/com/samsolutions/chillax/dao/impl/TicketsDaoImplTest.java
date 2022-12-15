package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.EventsDao;
import com.samsolutions.chillax.dao.OrdersDao;
import com.samsolutions.chillax.dao.TicketsDao;
import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.entity.Tickets;
import com.samsolutions.chillax.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration("classpath:application-context-test.xml")
@ExtendWith(SpringExtension.class)
@Transactional
public class TicketsDaoImplTest
{

    @Autowired
    private TicketsDao ticketsDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private EventsDao eventsDao;

    @Autowired
    private OrdersDao ordersDao;

    private List<Tickets> ticketsList = new ArrayList<>();

    @BeforeEach
    public void setUp()
    {
        Users user = new Users(0, "root", "root", "Luke", "Skywalker", "email@gmail.com", "+375296666666", null);
        user.setIdUser(usersDao.create(user));
        Orders order = new Orders(0, "2a4a1567-a4a6-4ae6-9cc0-8e3094aae60e", 100, 10, Date.valueOf("2018-10-28"), false, user);
        order.setIdOrders(ordersDao.create(order));
        Events event = new Events(0, "Brit Floyd", "The World's Greatest Pink Floyd Tribute Show", "britFloyd.jpg", Timestamp.valueOf("2018-11-24 20:00:00.000"), 100.00, 1500, null, null);
        event.setIdEvent(eventsDao.create(event));
        Tickets ticket1 = new Tickets(0, "c94f17f6-ac38-4c37-b1b5-3a6f7075d927", false, event, order);
        Tickets ticket2 = new Tickets(0, "1a0d4222-ad4f-45e3-aa90-fb7475784486", false, event, order);
        ticket1.setIdTickets(ticketsDao.create(ticket1));
        ticket2.setIdTickets(ticketsDao.create(ticket2));
        ticketsList.add(ticket1);
        ticketsList.add(ticket2);
    }

    @Test
    public void create()
    {
        Tickets actTicket = new Tickets(0, "9a0d7685-as4f-45e3-aa90-fb7475784444", false, null, null);
        int id = ticketsDao.create(actTicket);
        actTicket.setIdTickets(id);
        assertEquals(ticketsDao.getByPK(id).toString(), actTicket.toString());
    }

    @Test
    public void getByPK()
    {
        Tickets expTicket = ticketsList.get(0);
        Tickets actTicket = ticketsDao.getByPK(expTicket.getIdTickets());
        assertNotNull(actTicket);
        assertEquals(expTicket.toString(), actTicket.toString());
    }

    @Test
    public void getAll()
    {
        List<Tickets> list = ticketsDao.getAll();
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(ticketsList, list);
    }

    @Test
    public void update()
    {
        Tickets expTicket = ticketsList.get(0);
        int idTicket = expTicket.getIdTickets();
        Tickets ticketsUpdate = new Tickets(idTicket, "1981efbd-7ab6-4d53-bfac-199229899ba8", true, null, null);
        ticketsDao.update(ticketsUpdate);
        Tickets actTicket = ticketsDao.getByPK(idTicket);
        assertNotEquals(expTicket.toString(), actTicket.toString());
        assertEquals(ticketsUpdate.toString(), actTicket.toString());
    }

    @Test
    public void delete()
    {
        int idTicket = ticketsList.get(0).getIdTickets();
        ticketsDao.delete(idTicket);
        assertNull(ticketsDao.getByPK(idTicket));
    }

    @Test
    public void getNumberOfTicketsByEvent()
    {
        int ticketsNumber = ticketsDao.getNumberOfTicketsByEvent(ticketsList.get(0).getEvent().getIdEvent()).intValue();
        assertTrue(ticketsNumber != 0);
        assertEquals(2, ticketsNumber);
    }

    @Test
    public void getAllByOrder()
    {
        List<Tickets> list = ticketsDao.getAllByOrder(ticketsList.get(0).getOrder().getGuid(), "root");
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(ticketsList, list);
    }

    @Test
    public void getCountTicketsByOrderAndEvent()
    {
        Orders order = ticketsList.get(0).getOrder();
        int ticketsNumber = ticketsDao.getCountTicketsByOrderAndEvent(order.getGuid(), ticketsList.get(0).getEvent().getIdEvent(), order.getUser().getIdUser());
        assertTrue(ticketsNumber != 0);
        assertEquals(2, ticketsNumber);
    }

    @Test
    public void getByGuid()
    {
        Tickets expTicket = ticketsList.get(0);
        Tickets actTicket = ticketsDao.getByGuid(expTicket.getGuid(), "root");
        assertNotNull(actTicket);
        assertEquals(expTicket.toString(), actTicket.toString());
    }
}