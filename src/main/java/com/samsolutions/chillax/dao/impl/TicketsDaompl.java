package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.TicketsDao;
import com.samsolutions.chillax.entity.Tickets;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Repository
public class TicketsDaompl implements TicketsDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = getLogger(TicketsDaompl.class);

    public int create(Tickets tickets)
    {
        Session session = sessionFactory.getCurrentSession();
        return (int)session.save(tickets);
    }

    public Tickets getByPK(int key)
    {
        Tickets tickets;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Tickets where id_tickets = :key");
            query.setParameter("key", key);
            tickets = (Tickets) query.getSingleResult();
        }catch (NoResultException e)
        {
            tickets = null;
            logger.debug("No result with such PK in Tickets or access is denied");
        }
        return tickets;
    }

    public void update(Tickets tickets)
    {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.update(tickets);
    }

    public void delete(int key)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Tickets where id_tickets = :key");
        query.setParameter("key", key);
        session.delete((Tickets)query.getSingleResult());
    }

    public List<Tickets> getAll()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Tickets").list();
    }

    public Long getNumberOfTicketsByEvent(int idEvent)
    {
        Long numberOfTickets = null;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("select count(id_tickets) from Tickets where ID_EVENT_FK = :key");
            query.setParameter("key", idEvent);
            numberOfTickets = (Long)query.uniqueResult();
        }catch (NoResultException e)
        {
            logger.debug("No Tickets for this event in Tickets");
        }
        return numberOfTickets;
    }

    public List<Tickets> getAllByOrder(String orderGuid, String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select t.* from Tickets t join Orders o on t.ID_ORDER_FK = o.ID_ORDERS join Users u on o.ID_USER_FK = u.ID_USER where o.GUID = :guid and u.LOGIN = :login").addEntity(Tickets.class);
        query.setParameter("guid", orderGuid);
        query.setParameter("login", login);
        List<Tickets> list = query.list();
        return list;
    }

    public int getCountTicketsByOrderAndEvent(String orderGuid, int idEvent, int idUser) {
        Integer countOfTickets = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select count(t.ID_TICKETS) as count from Tickets t join Orders o on o.ID_ORDERS = t.ID_ORDER_FK join Events e on t.ID_EVENT_FK = e.ID_EVENT where o.GUID = :guid AND t.ID_EVENT_FK = :idEvent AND (o.ID_USER_FK = :id or 1 = :id)").addScalar("count", IntegerType.INSTANCE);
            query.setParameter("guid", orderGuid);
            query.setParameter("idEvent", idEvent);
            query.setParameter("id", idUser);
            countOfTickets = (int) query.uniqueResult();
        } catch (NoResultException e) {
            logger.debug("No Tickets for this order and event in Tickets");
        }
        return countOfTickets;
    }

    public Tickets getByGuid(String guid, String login){
        Tickets tickets;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select t.* from Tickets t join ORDERS O on t.ID_ORDER_FK = O.ID_ORDERS join USERS U on O.ID_USER_FK = U.ID_USER where t.GUID = :guid and u.LOGIN = :login").addEntity(Tickets.class);
            query.setParameter("guid", guid);
            query.setParameter("login", login);
            tickets = (Tickets) query.getSingleResult();
        }catch (NoResultException e)
        {
            tickets = null;
            logger.debug("No result with such PK in Tickets or access is denied");
        }
        return tickets;
    }
}