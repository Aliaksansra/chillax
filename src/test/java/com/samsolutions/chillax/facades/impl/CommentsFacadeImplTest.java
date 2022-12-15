package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.CommentsConverterFromDTO;
import com.samsolutions.chillax.converters.CommentsConverterToDTO;
import com.samsolutions.chillax.dto.CommentsDTO;
import com.samsolutions.chillax.entity.Comments;
import com.samsolutions.chillax.services.CommentsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;

public class CommentsFacadeImplTest
{

    @Mock
    private CommentsService commentsService;

    @Mock
    private CommentsConverterToDTO converterToDTO;

    @Mock
    private CommentsConverterFromDTO converterFromDTO;

    @InjectMocks
    private CommentsFacadeImpl commentsFacade;

    private static List<CommentsDTO> commentsDTOList = new ArrayList<>();

    private static List<Comments> commentsList = new ArrayList<>();

    @BeforeAll
    public static void setUp()
    {
        commentsDTOList.add(new CommentsDTO(1, "Cool", Date.valueOf("2018-12-28"), 1, "admin"));
        commentsDTOList.add(new CommentsDTO(2, "Great", Date.valueOf("2018-11-28"), 1, "admin"));
        commentsList.add(new Comments(1, "Cool", Date.valueOf("2018-12-28"), null, null));
        commentsList.add(new Comments(2, "Great", Date.valueOf("2018-11-28"), null, null));
    }

    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCommentsByEvent()
    {
        when(commentsService.getCommentsByEvent(1)).thenReturn(commentsList);
        when(converterToDTO.convert(commentsList.get(0))).thenReturn(commentsDTOList.get(0));
        when(converterToDTO.convert(commentsList.get(1))).thenReturn(commentsDTOList.get(1));
        List<CommentsDTO> actList = commentsFacade.getCommentsByEvent(1);
        assertEquals(commentsDTOList, actList);
        assertNotNull(actList);
    }

    @Test
    public void addComment()
    {
        CommentsDTO newCommentDTO = commentsDTOList.get(0);
        Comments newComment = commentsList.get(0);
        when(converterFromDTO.convert(newCommentDTO)).thenReturn(newComment);
        doNothing().when(commentsService).createComment(newComment);
        commentsFacade.addComment(newCommentDTO);
        verify(converterFromDTO).convert(newCommentDTO);
        verify(commentsService).createComment(newComment);
    }

    @Test
    public void deleteComment()
    {
        when(commentsService.deleteComment(anyInt(), eq("admin"))).thenReturn(true);
        boolean result = commentsFacade.deleteComment(0, "admin");
        assertTrue(result);
    }

    @Test
    public void updateComment()
    {
        Comments updateComment = commentsList.get(0);
        CommentsDTO updateCommentDTO = commentsDTOList.get(0);
        when(commentsService.updateComment(updateComment, "admin")).thenReturn(updateComment);
        when(converterFromDTO.convert(updateCommentDTO)).thenReturn(updateComment);
        when(converterToDTO.convert(updateComment)).thenReturn(updateCommentDTO);
        CommentsDTO actUpdateCommentDTO = commentsFacade.updateComment(updateCommentDTO, "admin");
        assertEquals(updateCommentDTO, actUpdateCommentDTO);
    }

    @Test
    public void updateCommentIfWrongUser()
    {
        Comments updateComment = commentsList.get(0);
        CommentsDTO updateCommentDTO = commentsDTOList.get(0);
        when(commentsService.updateComment(updateComment, "wrong user")).thenReturn(null);
        when(converterFromDTO.convert(updateCommentDTO)).thenReturn(updateComment);
        CommentsDTO result = commentsFacade.updateComment(updateCommentDTO, "wrong user");
        assertTrue(result == null);
    }
}