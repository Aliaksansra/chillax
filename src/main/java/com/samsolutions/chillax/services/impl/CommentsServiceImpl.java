package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.CommentsDao;
import com.samsolutions.chillax.entity.Comments;
import com.samsolutions.chillax.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService
{

    @Autowired
    private CommentsDao commentsDao;

    public List<Comments> getCommentsByEvent(int idEvent)
    {
        return commentsDao.getAllByEvent(idEvent);
    }

    public void createComment(Comments comment)
    {
        commentsDao.create(comment);
    }

    public boolean deleteComment(int id, String login)
    {
        return commentsDao.delete(id, login);
    }

    public Comments updateComment(Comments comment, String login)
    {
        Comments newComment = commentsDao.getByPK(comment.getIdComments(), login);
        if(newComment != null)
        {
            newComment.setComment(comment.getComment());
            commentsDao.update(newComment);
        }
        return newComment;
    }
}