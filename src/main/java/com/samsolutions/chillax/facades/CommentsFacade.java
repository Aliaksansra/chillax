package com.samsolutions.chillax.facades;

import com.samsolutions.chillax.converters.CommentsConverterFromDTO;
import com.samsolutions.chillax.converters.CommentsConverterToDTO;
import com.samsolutions.chillax.dto.CommentsDTO;
import com.samsolutions.chillax.entity.Comments;
import com.samsolutions.chillax.services.CommentsService;

import java.util.List;

/**
 * This interface implements a pattern Facade.
 * Declares methods to manage the service and converters
 * that work with Comments and CommentsDTO classes.
 *
 * @see CommentsService
 * @see CommentsConverterToDTO
 * @see CommentsConverterFromDTO
 * @see CommentsDTO
 * @see Comments
 */
public interface CommentsFacade
{
    /**
     * Gets all comments by event id.
     * Converts received entities to DTO and returns them.
     *
     * @param idEvent event id
     * @return List<CommentsDTO>
     */
    List<CommentsDTO> getCommentsByEvent(int idEvent);

    /**
     * Converts received DTO into entities and calls service for creating new comment.
     *
     * @param comment new comment
     */
    void addComment(CommentsDTO comment);

    /**
     * Calls service for deleting comment by id and user login.
     *
     * @param id comment id
     * @param login user login
     * @return true - if comment is deleted, false - if not
     */
    boolean deleteComment(int id, String login);

    /**
     * Converts received CommentsDTO into entities and calls service
     * to update comment according to the user login.
     *
     * @param commentsDTO modified comment
     * @param login user login
     * @return CommentsDTO
     */
    CommentsDTO updateComment(CommentsDTO commentsDTO, String login);
}