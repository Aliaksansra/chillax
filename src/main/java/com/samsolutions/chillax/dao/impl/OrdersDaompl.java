package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.OrdersDao;
import com.samsolutions.chillax.entity.Orders;
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
public class OrdersDaompl implements OrdersDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = getLogger(OrdersDaompl.class);

    public int create(Orders orders)
    {
        Session session = sessionFactory.getCurrentSession();
        return (int)session.save(orders);
    }

    public Orders getByPK(int key, String login)
    {
        Orders orders;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery( "from Orders where ID_ORDERS = :key");
            query.setParameter("key", key);
            orders = (Orders) query.getSingleResult();
        }catch (NoResultException e)
        {
            orders = null;
            logger.debug("No result with such PK in ORDERS");
        }
        return orders;
    }

    public void update(Orders orders)
    {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.update(orders);
    }

    public boolean delete(String orderGuid, String login)
    {
        boolean result = false;
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select o.* from Orders o join Users u on o.ID_USER_FK = u.ID_USER where o.GUID = :guid and u.LOGIN = :login").addEntity(Orders.class);
            query.setParameter("guid", orderGuid);
            query.setParameter("login", login);
            Orders order = (Orders)query.getSingleResult();
            if(order != null)
            {
                session.delete(order);
                result = true;
            }
        }catch (NoResultException e)
        {
            logger.debug("No result with such Guid in Orders", e);
        }
        return result;
    }

    public List<Orders> getAll()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Orders").list();
    }

    public List<Orders> getAllByUserPK(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Orders where ID_USER_FK = :key");
        query.setParameter("key", id);
        return query.list();
    }

    public void deleteAllUnpaid()
    {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Orders where PAID = false").executeUpdate();
    }

    public Orders getByGuid(String guid, String login){
        Orders orders;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select o.* from Orders o inner join Users u on o.ID_USER_FK = u.ID_USER where o.GUID = :guid and u.LOGIN = :login").addEntity(Orders.class);
            query.setParameter("guid", guid);
            query.setParameter("login", login);
            orders = (Orders) query.getSingleResult();
        }catch (NoResultException e)
        {
            orders = null;
            logger.debug("No result with such PK in ORDERS");
        }
        return orders;
    }
}