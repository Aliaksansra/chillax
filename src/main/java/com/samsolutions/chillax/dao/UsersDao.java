package com.samsolutions.chillax.dao;

import com.samsolutions.chillax.entity.Users;
import java.util.List;

/**
 * This interface declares methods of CRUD operations
 * to work with entities "Users" and transactions to the database.
 *
 * @see Users
 */
public interface UsersDao
{
    /**
     * Creates new transmitted user in the database.
     *
     * @param user new user
     * @return id of the created user
     */
    int create(Users user);

    /**
     * Gets user from the database by primary key.
     *
     * @param key primary key
     * @return Users
     */
    Users getByPK(int key);

    /**
     * Updates user in the database.
     *
     * @param user modified user
     */
    void update(Users user);

    /**
     * Deletes user from the database by primary key.
     *
     * @param key primary key
     */
    void delete(int key);

    /**
     * Gets user from the database by user login.
     *
     * @param login user login
     * @return Users
     */
    Users getByLogin(String login);

    /**
     * Gets user from the database by email.
     *
     * @param email email
     * @return Users
     */
    Users getByEmail(String email);

    /**
     * Gets all users from the database.
     *
     * @return List<Users>
     */
    List<Users> getAll();
}