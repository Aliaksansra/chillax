package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.EventsDao;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventsServiceImpl implements EventsService
{
    @Autowired
    private EventsDao eventsDao;

    public List<Events> getEvents(int page, int pageSize)
    {
        List<Events> listEvents = eventsDao.getAllForPage(page, pageSize);
        return listEvents;
    }

    public List<Events> getEventsByType(int page, int pageSize, int type)
    {
        List<Events> listEventsByName = eventsDao.getAllByTypeForPage(page, pageSize, type);
        return listEventsByName;
    }

    public Events getEventByPK(int id)
    {
        return eventsDao.getByPK(id);
    }

    public List<Events> getAllEvents()
    {
        return eventsDao.getAll();
    }

    public List<Events> getAllEvents(int type)
    {
        List<Events> listEventsByType = eventsDao.getAllByType(type);
        return listEventsByType;
    }

    public void eventUpdate(Events event)
    {
        if(event.getPoster() == null)
        {
            event.setPoster(eventsDao.getByPK(event.getIdEvent()).getPoster());
        }
        eventsDao.update(event);
    }

    public void eventDelete(int id)
    {
        eventsDao.delete(id);
    }

    public void addEvent(Events event)
    {
        eventsDao.create(event);
    }

    public List<Events> getOrderedEvents(String orderGuid, String login)
    {
        return eventsDao.getOrderedEvents(orderGuid, login);
    }

    public boolean checkUniqueImage(String posterName) {
        return eventsDao.isUniquePosterName(posterName);
    }
}