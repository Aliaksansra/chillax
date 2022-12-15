package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.EventsDao;
import com.samsolutions.chillax.dao.TypesDao;
import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.dao.OrdersDao;
import com.samsolutions.chillax.dao.TicketsDao;

import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.entity.Types;
import com.samsolutions.chillax.entity.Users;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.entity.Tickets;

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

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration("classpath:application-context-test.xml")
@ExtendWith(SpringExtension.class)
@Transactional
public class EventsDaoImplTest
{

    @Autowired
    private EventsDao eventsDao;

    @Autowired
    private TypesDao typesDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private TicketsDao ticketsDao;

    private List<Events> eventsList = new ArrayList<>();

    @BeforeEach
    public void setUp()
    {
        Types type = new Types(0, "Music");
        type.setIdType(typesDao.create(type));
        Events event1 = new Events(0, "Brit Floyd", "The World's Greatest Pink Floyd Tribute Show", "britFloyd.jpg", Timestamp.valueOf("2018-11-24 20:00:00.000"), 100.00, 1500, type, null);
        Events event2 = new Events(0, "Ляпис 98", "Радикальный шансон", "trubetskoj.jpg", Timestamp.valueOf("2018-12-29 19:00:00.000"), 80.00, 1000, type, null);
        event1.setIdEvent(eventsDao.create(event1));
        event2.setIdEvent(eventsDao.create(event2));
        eventsList.add(event1);
        eventsList.add(event2);
    }

    @Test
    public void create()
    {
        Events actEvent = new Events(0, "Динамо-Минск - СКА", "Хоккейный матч Динамо-Минск - СКА", "cka.jpg", Timestamp.valueOf("2019-01-04 19:30:00.000"), 7.00, 15000, null, null);
        int id = eventsDao.create(actEvent);
        actEvent.setIdEvent(id);
        assertEquals(eventsDao.getByPK(id).toString(), actEvent.toString());
    }

    @Test
    public void getByPK()
    {
        Events expEvent = eventsList.get(0);
        Events actEvent = eventsDao.getByPK(expEvent.getIdEvent());
        assertNotNull(actEvent);
        assertEquals(expEvent.toString(), actEvent.toString());
    }

    @Test
    public void getAll()
    {
        List<Events> list = eventsDao.getAll();
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(eventsList, list);
    }

    @Test
    public void update()
    {
        Events expEvent = eventsList.get(0);
        int idEvent = expEvent.getIdEvent();
        Events eventUpdate = new Events(idEvent, "Ляпис 98", "Космический цирк аутсайдеров", "trubetskoj.jpg", Timestamp.valueOf("2018-12-29 21:00:00.000"), 90.00, 1000, null, null);
        eventsDao.update(eventUpdate);
        Events actEvent = eventsDao.getByPK(idEvent);
        assertNotEquals(expEvent.toString(), actEvent.toString());
        assertEquals(eventUpdate.toString(), actEvent.toString());
    }

    @Test
    public void delete()
    {
        int idEvent = eventsList.get(0).getIdEvent();
        eventsDao.delete(idEvent);
        assertNull(eventsDao.getByPK(idEvent));
    }

    @Test
    public void getOrderedEvents()
    {
        Users user = new Users(0, "root", "root", "Luke", "Skywalker", "email@gmail.com", "+375296666666", null);
        user.setIdUser(usersDao.create(user));
        Orders order = new Orders(0, "2a4a1567-a4a6-4ae6-9cc0-8e3094aae60e", 180, 0, Date.valueOf("2018-12-28"), false, user);
        order.setIdOrders(ordersDao.create(order));
        ticketsDao.create(new Tickets(0, "c94f17f6-ac38-4c37-b1b5-3a6f7075d927", false, eventsList.get(0), order));
        ticketsDao.create(new Tickets(0, "1a0d4222-ad4f-45e3-aa90-fb7475784486", false, eventsList.get(1), order));
        List<Events> list = eventsDao.getOrderedEvents(order.getGuid(), "root");
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(eventsList, list);
    }

    @Test
    public void getAllByTypeForPage()
    {
        List<Events> list = eventsDao.getAllByTypeForPage(1, 12, eventsList.get(0).getType().getIdType());
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(eventsList, list);
    }

    @Test
    public void getAllByType()
    {
        List<Events> list = eventsDao.getAllByType(eventsList.get(0).getType().getIdType());
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(eventsList, list);
    }

    @Test
    public void getAllForPage()
    {
        List<Events> list = eventsDao.getAllForPage(1, 12);
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(eventsList, list);
    }

    @Test
    public void isUniquePosterName()
    {
        assertTrue(eventsDao.isUniquePosterName("uniqueimage.jpg"));
        assertFalse(eventsDao.isUniquePosterName("britFloyd.jpg"));
    }
}