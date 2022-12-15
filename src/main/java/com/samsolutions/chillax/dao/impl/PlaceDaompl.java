package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.PlaceDao;
import com.samsolutions.chillax.entity.Place;
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
public class PlaceDaompl implements PlaceDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = getLogger(PlaceDaompl.class);

    public int create(Place place)
    {
        Session session = sessionFactory.getCurrentSession();
        return (int)session.save(place);
    }

    public Place getByPK(int key)
    {
        Place place;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Place where id_place = :key");
            query.setParameter("key", key);
            place = (Place)query.getSingleResult();
        }catch (NoResultException e)
        {
            place = null;
            logger.debug("No result with such PK in Place");
        }
        return place;
    }

    public void update(Place place)
    {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.update(place);
    }

    public void delete(int key)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Place where id_place = :key");
        query.setParameter("key", key);
        session.delete((Place)query.getSingleResult());
    }

    public List<Place> getAll(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Place").list();
    }
}