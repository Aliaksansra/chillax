package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.CommentsConverterFromDTO;
import com.samsolutions.chillax.converters.CommentsConverterToDTO;
import com.samsolutions.chillax.dto.CommentsDTO;
import com.samsolutions.chillax.entity.Comments;
import com.samsolutions.chillax.services.CommentsService;
import com.samsolutions.chillax.facades.CommentsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentsFacadeImpl implements CommentsFacade
{
    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsConverterToDTO converterToDTO;

    @Autowired
    private CommentsConverterFromDTO converterFromDTO;

    public List<CommentsDTO> getCommentsByEvent(int idEvent)
    {
        List<Comments> list = commentsService.getCommentsByEvent(idEvent);
        List<CommentsDTO> listDTO = new ArrayList<>();
        for (Comments comment : list)
        {
            listDTO.add(converterToDTO.convert(comment));
        }
        return listDTO;
    }

    public void addComment(CommentsDTO comment)
    {
        commentsService.createComment(converterFromDTO.convert(comment));
    }

    public boolean deleteComment(int id, String login)
    {
        return commentsService.deleteComment(id, login);
    }

    public CommentsDTO updateComment(CommentsDTO commentsDTO, String login)
    {
        Comments comment = commentsService.updateComment(converterFromDTO.convert(commentsDTO), login);
        if(comment != null)
        {
            return converterToDTO.convert(comment);
        }else
            {
            return null;
        }
    }
}