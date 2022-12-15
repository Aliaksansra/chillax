package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.CommentsDTO;
import com.samsolutions.chillax.facades.CommentsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * This RestController works with comments,
 * gets and sends them from and to the CommentsFacade.
 *
 * @see CommentsFacade
 */
@RestController
@RequestMapping("/api/comments")
public class CommentsController
{
    /**
     * Automatic injects of the CommentsFacade class that works with comment entities and DTO.
     */
    @Autowired
    private CommentsFacade commentsFacade;

    /**
     * Gets a new CommentsDTO, checks the user is authorized or not.
     * If the user is authorized, the CommentsFacade will be called to create a new comment in the database.
     *
     * @param commentsDTO CommentsFacade
     * @return ResponseEntity with HttpStatus.OK if the user is authorized
     * or ResponseEntity with HttpStatus.BAD_REQUEST if not.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> add(@RequestBody CommentsDTO commentsDTO)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        if(!login.equals("guest"))
        {
            commentsDTO.setLogin(login);
            commentsFacade.addComment(commentsDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gets a modified CommentsDTO. Calls the CommentsFacade to update comment in the database.
     * Also sends authorized user login to the CommentsFacade to check
     * whether this comment belongs to him or not. Returns the updated CommentsDTO.
     *
     * @param commentsDTO CommentsDTO
     * @return modified CommentsDTO
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody CommentsDTO update(@RequestBody CommentsDTO commentsDTO)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return commentsFacade.updateComment(commentsDTO, auth.getName());
    }

    /**
     * Gets comment id and calls CommentsFacade to delete comment in the database by id.
     * Also sends authorized user login to the CommentsFacade to check
     * whether this comment belongs to him or not.
     *
     * @param idComments comment id
     * @return ResponseEntity with HttpStatus.OK if the comment is deleted
     * or ResponseEntity with HttpStatus.BAD_REQUEST if not.
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") int idComments)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean result = commentsFacade.deleteComment(idComments, auth.getName());
        if(result)
        {
            return new ResponseEntity<>(0, HttpStatus.OK);
        }else
            {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }
}