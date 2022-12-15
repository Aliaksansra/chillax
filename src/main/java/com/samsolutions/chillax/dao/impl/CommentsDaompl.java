package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.CommentsDao;
import com.samsolutions.chillax.entity.Comments;
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
public class CommentsDaompl implements CommentsDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = getLogger(CommentsDaompl.class);

    public int create(Comments comment)
    {
        Session session = sessionFactory.getCurrentSession();
        return (int)session.save(comment);
    }

    public Comments getByPK(int key, String login)
    {
        Comments comment;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select comment.* from Comments comment inner join USERS users on comment.ID_USER_FK = users.ID_USER where comment.id_comments = :key and users.LOGIN = :login").addEntity(Comments.class);
            query.setParameter("key", key);
            query.setParameter("login", login);
            comment = (Comments) query.getSingleResult();
        }catch (NoResultException e)
        {
            comment = null;
            logger.debug("No result with such PK in Comments", e);
        }
        return comment;
    }

    public void update(Comments comment)
    {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        session.update(comment);
    }

    public boolean delete(int key, String login)
    {
        boolean result = false;
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createSQLQuery("select comment.* from Comments comment inner join USERS users on comment.ID_USER_FK = users.ID_USER where comment.id_comments = :key and (users.LOGIN = :login or 'admin' = :login)").addEntity(Comments.class);
            query.setParameter("key", key);
            query.setParameter("login", login);
            Comments comment = (Comments) query.getSingleResult();
            if(comment != null)
            {
                session.delete(comment);
                result = true;
            }
        }catch (NoResultException e)
        {
            logger.debug("No result with such PK in Comments", e);
        }
        return result;
    }

    public List<Comments> getAll()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Comments").list();
    }

    public List<Comments> getAllByEvent(int idEvent)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Comments where ID_EVENT_FK = :id order by ID_COMMENTS desc");
        query.setParameter("id", idEvent);
        return query.list();
    }
}