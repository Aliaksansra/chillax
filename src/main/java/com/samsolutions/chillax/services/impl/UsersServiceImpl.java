package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.RolesDao;
import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.entity.Users;
import com.samsolutions.chillax.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService
{
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private RolesDao rolesDao;

    public void saveNewUser(Users user, String role)
    {
        user.setRole(rolesDao.getByName(role));
        usersDao.create(user);
    }

    public Users getByLogin(String login)
    {
        return usersDao.getByLogin(login);
    }

    public Users getByEmail(String email)
    {
        return usersDao.getByEmail(email);
    }

    public List<Users> getAllUsers()
    {
        return usersDao.getAll();
    }

    public void deleteUser(int id)
    {
        usersDao.delete(id);
    }

    public Users getUserByPK(int id)
    {
       return usersDao.getByPK(id);
    }

    public void registerUser(Users user)
    {
        usersDao.create(user);
    }
}