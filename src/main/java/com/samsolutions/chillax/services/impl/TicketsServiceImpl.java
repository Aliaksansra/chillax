package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.EventsDao;
import com.samsolutions.chillax.dao.TicketsDao;
import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.entity.Tickets;
import com.samsolutions.chillax.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketsServiceImpl implements TicketsService
{
    @Autowired
    private TicketsDao ticketsDao;

    @Autowired
    private EventsDao eventsDao;

    @Autowired
    private UsersDao usersDao;

    public int getTicketsNumberByEvent(int idEvent)
    {
       return eventsDao.getByPK(idEvent).getAllTickets() - ticketsDao.getNumberOfTicketsByEvent(idEvent).intValue();
    }

    public List<Tickets> getTicketsByOrder(String orderGuid, String login)
    {
        return ticketsDao.getAllByOrder(orderGuid, login);
    }

    public Tickets getTicketByGuid(String guid, String login)
    {
        return ticketsDao.getByGuid(guid, login);
    }

    public int getCountTickets(String orderGuid, int idEvent, String login)
    {
        return ticketsDao.getCountTicketsByOrderAndEvent(orderGuid, idEvent, usersDao.getByLogin(login).getIdUser());
    }
}