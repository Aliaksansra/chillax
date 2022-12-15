package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.RolesDao;
import com.samsolutions.chillax.entity.Roles;
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
public class RolesDaoImpl implements RolesDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = getLogger(RolesDaoImpl.class);

    public int create(Roles role)
    {
        Session session = sessionFactory.getCurrentSession();
       return (int)session.save(role);
    }

    public Roles getByPK(int key)
    {
        Roles role;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Roles where id_role = :key");
            query.setParameter("key", key);
            role = (Roles) query.getSingleResult();
        }catch (NoResultException e)
        {
            role = null;
            logger.debug("No result with such PK in Roles");
        }
        return role;
    }

    public Roles getByName(String roleName)
    {
        Roles role;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Roles where role = :roleName");
            query.setParameter("roleName", roleName);
            role = (Roles) query.getSingleResult();
        }catch (NoResultException e)
        {
            role = null;
            logger.debug("No result with such role name in Roles");
        }
        return role;
    }

    public void update(Roles role)
    {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.update(role);
    }

    public void delete(int key)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Roles where id_role = :key");
        query.setParameter("key", key);
        session.delete((Roles) query.getSingleResult());
    }

    public List<Roles> getAll()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Roles").list();
    }
}