package com.samsolutions.chillax.dao;

import com.samsolutions.chillax.entity.Comments;
import java.util.List;
import com.samsolutions.chillax.entity.Events;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * This interface declares methods of CRUD operations
 * to work with entities "Comments" and transactions to the database.
 *
 * @see Comments
 */
public interface CommentsDao
{
    /**
     * Creates new transmitted comment in the database.
     *
     * @param comment new comment
     * @return comment id
     */
    int create(Comments comment);

    /**
     * Gets one comment from the database by primary key and user login.
     *
     * @param key primary key
     * @param login user login
     * @return comment
     */
    Comments getByPK(int key, String login);

    /**
     * Updates comment in the database.
     *
     * @param comment modified comment
     */
    void update(Comments comment);

    /**
     * Deletes comment from the database by primary key and user login.
     *
     * @param key primary key
     * @param login user login
     * @return true - if comment is deleted, false - if not
     */
    boolean delete(int key, String login);

    /**
     * Gets all comments from the database.
     *
     * @return List<Comments>
     */
    List<Comments> getAll();

    /**
     * Gets all comments from the database by event id.
     *
     * @param idEvent event id
     * @return List<Comments>
     * @see Events
     */
    List<Comments> getAllByEvent(int idEvent);

}