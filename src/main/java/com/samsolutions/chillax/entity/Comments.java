package com.samsolutions.chillax.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * This class describes entity "Comments"
 * according to fields of the table "COMMENTS" in the database.
 */
public class Comments implements Serializable
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
     *
     * @see Events
     */
    private Events event;

    /**
     * The user who writes this comment.
     *
     * @see Users
     */
    private Users user;

    /**
     * empty constructor
     */
    public Comments()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idComments comment id
     * @param comment comment
     * @param dateOfComment comment date
     * @param event comment event
     * @param user user who writes this comment
     */
    public Comments(int idComments, String comment, Date dateOfComment, Events event, Users user)
    {
        this.idComments = idComments;
        this.comment = comment;
        this.dateOfComment = dateOfComment;
        this.event = event;
        this.user = user;
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
     * Gets comment event.
     *
     * @return event
     */
    public Events getEvent()
    {
        return event;
    }

    /**
     * Sets comment event.
     *
     * @param event comment event
     */
    public void setEvent(Events event)
    {
        this.event = event;
    }

    /**
     * Gets user.
     *
     * @return user
     */
    public Users getUser()
    {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user user who writes this comment
     */
    public void setUser(Users user)
    {
        this.user = user;
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
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Comment: " + idComments + ", " + comment + ", " + dateOfComment;
    }
}
