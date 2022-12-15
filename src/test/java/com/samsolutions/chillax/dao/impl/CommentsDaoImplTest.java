package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.CommentsDao;
import com.samsolutions.chillax.dao.EventsDao;
import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.entity.Comments;
import com.samsolutions.chillax.entity.Events;
import com.samsolutions.chillax.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration("classpath:application-context-test.xml")
@ExtendWith(SpringExtension.class)
@Transactional
public class CommentsDaoImplTest
{

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private EventsDao eventsDao;

    private List<Comments> commentsList = new ArrayList<>();

    @BeforeEach
    public void setUp()
    {
        Users user = new Users(0, "root", "root", "Luke", "Skywalker", "email@gmail.com", "+375296666666", null);
        user.setIdUser(usersDao.create(user));
        Events event = new Events(0, "Brit Floyd", "The World's Greatest Pink Floyd Tribute Show", "britFloyd.jpg", Timestamp.valueOf("2018-11-24 20:00:00.000"), 100.00, 1500, null, null);
        event.setIdEvent(eventsDao.create(event));
        Comments comment1 = new Comments(0, "Pink Floyd пели лучше...", Date.valueOf("2018-10-28"), event, user);
        Comments comment2 = new Comments(0, "Отличный концерт!", Date.valueOf("2018-10-28"), event, user);
        comment1.setIdComments(commentsDao.create(comment1));
        comment2.setIdComments(commentsDao.create(comment2));
        commentsList.add(comment1);
        commentsList.add(comment2);
    }

    @Test
    public void create()
    {
        Comments actComment = new Comments(0, "Все супер!", Date.valueOf("2018-11-28"), null, commentsList.get(0).getUser());
        int id = commentsDao.create(actComment);
        actComment.setIdComments(id);
        assertEquals(commentsDao.getByPK(id, "root").toString(), actComment.toString());
    }

    @Test
    public void getAll()
    {
        List<Comments> list = commentsDao.getAll();
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void update()
    {
        Comments expComment = commentsList.get(0);
        Comments commentUpdate = new Comments(expComment.getIdComments(), "Отличный концерт, космическая музыка, жалко что трибьют, а не оригинал.", Date.valueOf("2018-10-28"), null, expComment.getUser());
        commentsDao.update(commentUpdate);
        Comments actComment = commentsDao.getByPK(expComment.getIdComments(), "root");
        assertNotEquals(expComment.toString(), actComment.toString());
        assertEquals(commentUpdate.toString(), actComment.toString());
    }

    @Test
    public void getByPK()
    {
        Comments expComment = commentsList.get(0);
        Comments actComment = commentsDao.getByPK(expComment.getIdComments(), "root");
        assertNotNull(actComment);
        assertEquals(expComment.toString(), actComment.toString());
    }

    @Test
    public void delete()
    {
        int idComment = commentsList.get(0).getIdComments();
        commentsDao.delete(idComment, "root");
        assertNull(commentsDao.getByPK(idComment, "root"));
    }

    @Test
    public void getAllByEvent()
    {
        List<Comments> list = commentsDao.getAllByEvent(commentsList.get(0).getEvent().getIdEvent());
        assertNotNull(list);
        assertEquals(2, list.size());
    }
}