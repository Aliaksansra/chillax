package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.EventsDao;
import com.samsolutions.chillax.entity.Events;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Repository
public class EventsDaompl implements EventsDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = getLogger(EventsDaompl.class);

    public int create(Events event)
    {
        Session session = sessionFactory.getCurrentSession();
        return (int)session.save(event);
    }

    public Events getByPK(int key)
    {
        Events event;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Events where id_event = :key");
            query.setParameter("key", key);
            event = (Events)query.getSingleResult();
        }catch (NoResultException e)
        {
            event = null;
            logger.debug("No result with such PK in Events");
        }
        return event;
    }

    public void update(Events event)
    {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.update(event);
    }

    public void delete(int key)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Events where id_event = :key");
        query.setParameter("key", key);
        session.delete((Events)query.getSingleResult());
    }

    public List<Events> getAllByTypeForPage(int page, int pageSize, int type)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Events where ID_TYPE_FK = :type order by DATE").setFirstResult(pageSize * (page-1)).setMaxResults(pageSize);
        query.setParameter("type", type);
        return query.list();
    }

    public List<Events> getAllForPage(int page, int pageSize)
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Events order by DATE").setFirstResult(pageSize * (page-1)).setMaxResults(pageSize).list();
    }

    public List<Events> getAll()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Events").list();
    }

    public List<Events> getAllByType(int type)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Events where ID_TYPE_FK = :type");
        query.setParameter("type", type);
        return query.list();
    }

    public List<Events> getOrderedEvents(String orderGuid, String login)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT DISTINCT e.* FROM EVENTS e join TICKETS t ON e.ID_EVENT = t.ID_EVENT_FK join ORDERS o ON t.ID_ORDER_FK = o.ID_ORDERS join USERS U on o.ID_USER_FK = U.ID_USER WHERE o.GUID = :guid and (U.LOGIN = :login or 'admin' = :login)").addEntity(Events.class);
        query.setParameter("guid", orderGuid);
        query.setParameter("login", login);
        return query.list();
    }

    public boolean isUniquePosterName(String posterName){
        boolean result = false;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(poster) from Events where poster = :name");
        query.setParameter("name", posterName);
        if((Long)query.uniqueResult() == 0){
            result = true;
        }
        return result;
    }
}