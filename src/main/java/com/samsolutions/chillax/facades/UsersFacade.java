package com.samsolutions.chillax.facades;

import com.samsolutions.chillax.dto.UsersDTO;
import com.samsolutions.chillax.entity.Users;
import com.samsolutions.chillax.converters.UsersConverterFromDTO;
import com.samsolutions.chillax.converters.UsersConverterToDTO;
import com.samsolutions.chillax.services.UsersService;

import java.util.List;

/**
 * This interface implements a pattern Facade.
 * Declares methods to manage the service and converters
 * that work with Users and UsersDTO classes.
 *
 * @see UsersService
 * @see UsersConverterToDTO
 * @see UsersConverterFromDTO
 * @see UsersDTO
 * @see Users
 */
public interface UsersFacade
{
    /**
     * Sets necessary role to the user, encodes password.
     * Converts received DTO into entities and calls service for adding new user.
     *
     * @param usersDTO new user
     * @param role user role name
     */
    void saveUser(UsersDTO usersDTO, String role);

    /**
     * Checks user data for uniqueness in the database using the service.
     *
     * @param userDTO user for checking
     * @param field field for checking
     * @return true - service return null so this user has unique fields, false - user isn`t unique
     */
    boolean checkUniqueUser(UsersDTO userDTO, String field);

    /**
     * Gets all users.
     * Converts received entities to DTO and returns them.
     *
     * @return List<UsersDTO>
     */
    List<UsersDTO> getUsers();

    /**
     * Calls service for deleting user by id.
     *
     * @param id Users id
     */
    void userDelete(int id);

    /**
     * Converts received DTO into entities and calls service for creating new user.
     *
     * @param usersDTO new user
     */
    void addUser(UsersDTO usersDTO);

    /**
     * Calls service for getting user by login.
     *
     * @param login user login
     * @return Users
     */
    Users getUserByLogin(String login);

    /**
     * Calls service for getting user by id.
     *
     * @param id Users id
     * @return Users
     */
    Users getUserByPK(int id);

    /**
     * Calls service for getting user by id.
     * Converts received entity to DTO and returns it.
     *
     * @param idUser Users id
     * @return UsersDTO
     */
    UsersDTO getUsersDTOByPK(int idUser);

    /**
     * Calls service for getting user by login.
     * Converts received entity to DTO and returns it.
     *
     * @param login user login
     * @return UsersDTO
     */
    UsersDTO getUserDTOByLogin(String login);
}