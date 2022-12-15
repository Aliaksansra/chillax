package com.samsolutions.chillax.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * This class describes fields
 * of DTO comments.
 */
public class CommentsDTO implements Serializable
{
    /**
     * comment id
     */
    private int idComments;

    /**
     * the content of the comment
     */
    private String comment;

    /**
     * comment date
     */
    private Date dateOfComment;

    /**
     * commentary event
     */
    private int idEvent;

    /**
     * The user login who writes this comment.
     */
    private String login;

    /**
     * empty constructor
     */
    public CommentsDTO()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idComments comment id
     * @param comment comment
     * @param dateOfComment comment date
     * @param idEvent event id of a comment
     * @param login user login who writes this comment
     */
    public CommentsDTO(int idComments, String comment, Date dateOfComment, int idEvent, String login) {
        this.idComments = idComments;
        this.comment = comment;
        this.dateOfComment = dateOfComment;
        this.idEvent = idEvent;
        this.login = login;
    }

    /**
     * Gets comment id.
     *
     * @return id
     */
    public int getIdComments()
    {
        return idComments;
    }

    /**
     * Sets comment id.
     *
     * @param idComments comment id
     */
    public void setIdComments(int idComments)
    {
        this.idComments = idComments;
    }

    /**
     * Gets comment.
     *
     * @return comment
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment comment
     */
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    /**
     * Gets comment date.
     *
     * @return date
     */
    public Date getDateOfComment()
    {
        return dateOfComment;
    }

    /**
     * Sets comment date.
     *
     * @param dateOfComment comment date
     */
    public void setDateOfComment(Date dateOfComment)
    {
        this.dateOfComment = dateOfComment;
    }

    /**
     * Gets event id.
     *
     * @return id
     */
    public int getIdEvent()
    {
        return idEvent;
    }

    /**
     * Sets event id.
     *
     * @param idEvent event id
     */
    public void setIdEvent(int idEvent)
    {
        this.idEvent = idEvent;
    }

    /**
     * Gets user login.
     *
     * @return login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * Sets user login.
     *
     * @param login user login
     */
    public void setLogin(String login)
    {
        this.login = login;
    }
}
