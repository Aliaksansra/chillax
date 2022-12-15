package com.samsolutions.chillax.services;

import com.samsolutions.chillax.entity.Comments;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.dao.CommentsDao;

import java.util.List;

/**
 * This interface declares methods
 * for a service that works with Comments DAO methods and entities.
 *
 * @see CommentsDao
 * @see Comments
 */
public interface CommentsService {

    /**
     * Gets comments list of the requested event
     * by Events id.
     *
     * @param idEvent event id
     * @return List<Comments>
     * @see Events
     */
    List<Comments> getCommentsByEvent(int idEvent);

    /**
     * Creates a received comment.
     *
     * @param comment new comment
     */
    void createComment(Comments comment);

    /**
     * Deletes comment by id and user login.
     *
     * @param id comment id
     * @param login user login
     * @return true - if comment is deleted, false - if not
     */
    boolean deleteComment(int id, String login);

    /**
     * Updates comment according to the user login.
     *
     * @param comment modified comment
     * @param login user login
     * @return updated comment
     */
    Comments updateComment(Comments comment, String login);
}