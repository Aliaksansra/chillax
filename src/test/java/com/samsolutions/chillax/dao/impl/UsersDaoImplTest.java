package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration("classpath:application-context-test.xml")
@ExtendWith(SpringExtension.class)
@Transactional
public class UsersDaoImplTest
{

    @Autowired
    private UsersDao usersDao;

    private Users expUser;

    @BeforeEach
    public void setUp()
    {
        expUser = new Users(0, "root", "root", "Luke", "Skywalker", "email@gmail.com", "+375296666666", null);
        expUser.setIdUser(usersDao.create(expUser));
    }

    @Test
    public void create()
    {
        Users actUser = new Users(0, "qwerty", "123", "Leonard", "Nimoy", "volcano@gmail.com", "+78447474745", null);
        int id = usersDao.create(actUser);
        actUser.setIdUser(id);
        assertEquals(usersDao.getByPK(id).toString(), actUser.toString());
    }

    @Test
    public void getByPK()
    {
        Users actUser = usersDao.getByPK(expUser.getIdUser());
        assertNotNull(actUser);
        assertEquals(expUser.toString(), actUser.toString());
    }

    @Test
    public void getAll()
    {
        Users user2 = new Users(0, "123", "321", "Duke", "Duke", "java@gmail.com", "+375291234567", null);
        usersDao.create(user2);
        List<Users> list = usersDao.getAll();
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void update()
    {
        int idUser = expUser.getIdUser();
        Users userUpdate = new Users(idUser, "ytrewq", "321", "Виктор", "Цой", "peremen@gmail.com", "+798563787575", null);
        usersDao.update(userUpdate);
        Users actUser = usersDao.getByPK(idUser);
        assertNotEquals(expUser.toString(), actUser.toString());
        assertEquals(userUpdate.toString(), actUser.toString());
    }

    @Test
    public void delete()
    {
        int idUser = expUser.getIdUser();
        usersDao.delete(idUser);
        assertNull(usersDao.getByPK(idUser));
    }

    @Test
    public void getByLogin()
    {
        Users actUser = usersDao.getByLogin("root");
        assertNotNull(actUser);
        assertEquals(expUser.toString(), actUser.toString());
    }

    @Test
    public void getByEmail()
    {
        Users actUser = usersDao.getByEmail("email@gmail.com");
        assertNotNull(actUser);
        assertEquals(expUser.toString(), actUser.toString());
    }
}