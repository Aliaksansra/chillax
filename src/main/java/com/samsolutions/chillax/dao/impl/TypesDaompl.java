package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.TypesDao;
import com.samsolutions.chillax.entity.Types;
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
public class TypesDaompl implements TypesDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = getLogger(TypesDaompl.class);

    public int create(Types type)
    {
        Session session = sessionFactory.getCurrentSession();
        return (int)session.save(type);
    }

    public Types getByPK(int key)
    {
        Types type;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Types where id_type = :key");
            query.setParameter("key", key);
            type = (Types) query.getSingleResult();
        }catch (NoResultException e)
        {
            type = null;
            logger.debug("No result with such PK in Types");
        }
        return type;
    }

    public void update(Types type)
    {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.update(type);
    }

    public void delete(int key)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Types where id_type = :key");
        query.setParameter("key", key);
        session.delete((Types)query.getSingleResult());
    }

    public List<Types> getAll()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Types").list();
    }
}