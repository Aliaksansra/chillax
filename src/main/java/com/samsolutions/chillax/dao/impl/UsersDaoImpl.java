package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.entity.Users;
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
public class UsersDaoImpl implements UsersDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = getLogger(UsersDaoImpl.class);

    public int create(Users user)
    {
        Session session = sessionFactory.getCurrentSession();
        return (int)session.save(user);
    }

    public Users getByPK(int key)
    {
        Users user;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Users where id_user = :key");
            query.setParameter("key", key);
            user = (Users)query.getSingleResult();
        }catch (NoResultException e)
        {
            user = null;
            logger.debug("No result with such PK in Users");
        }
        return user;
    }

    public Users getByLogin(String login)
    {
        Users user;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Users where login = :login");
            query.setParameter("login", login);
            user = (Users) query.getSingleResult();
        }catch (NoResultException e)
        {
            user = null;
            logger.debug("No user with such name");
        }
        return user;
    }

    public Users getByEmail(String email)
    {
        Users user;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Users where email = :email");
            query.setParameter("email", email);
            user = (Users) query.getSingleResult();
        }catch (NoResultException e)
        {
            user = null;
            logger.debug("No user with such email");
        }
        return user;
    }

    public void update(Users user)
    {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.update(user);
    }

    public void delete(int key)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Users where id_user = :key");
        query.setParameter("key", key);
        session.delete((Users)query.getSingleResult());
    }

    public List<Users> getAll()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Users").list();
    }
}