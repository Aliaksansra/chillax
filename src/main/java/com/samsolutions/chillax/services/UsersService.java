package com.samsolutions.chillax.services;

import com.samsolutions.chillax.entity.Users;
import com.samsolutions.chillax.dao.EventsDao;

import java.util.List;

/**
 * This interface declares methods
 * for a service that works with Users DAO methods and entities.
 *
 * @see EventsDao
 * @see Users
 */
public interface UsersService
{
    /**
     * Creates a received user with received role.
     *
     * @param user new user
     * @param role user role
     */
    void saveNewUser(Users user, String role);

    /**
     * Gets user by login.
     *
     * @param login user login
     * @return Users
     */
    Users getByLogin(String login);

    /**
     * Gets user by email.
     *
     * @param email user email
     * @return Users
     */
    Users getByEmail(String email);

    /**
     * Gets all users.
     *
     * @return List<Users>
     */
    List<Users> getAllUsers();

    /**
     * Deletes user by id.
     *
     * @param id user id
     */
    void deleteUser(int id);

    /**
     * Gets user by id.
     *
     * @param id user id
     * @return Users
     */
    Users getUserByPK(int id);

    /**
     * Saves new user with role "user".
     *
     * @param user new user
     */
    void registerUser(Users user);
}