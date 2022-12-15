package com.samsolutions.chillax.dto;

import java.io.Serializable;

/**
 * This class describes fields of DTO users.
 */
public class UsersDTO implements Serializable
{
    /**
     * user id
     */
    private int idUser;

    /**
     * user login
     */
    private String login;

    /**
     * user password
     */
    private String password;

    /**
     * user name
     */
    private String name;

    /**
     * user surname
     */
    private String surname;

    /**
     * user email
     */
    private String email;

    /**
     * user phone number
     */
    private String phone;

    /**
     * user role
     */
    private RolesDTO role;

    /**
     * empty constructor
     */
    public UsersDTO()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idUser user id
     * @param login user login
     * @param password user password
     * @param name user name
     * @param surname user surname
     * @param email user email
     * @param phone user phone
     * @param role user role
     */
    public UsersDTO(int idUser, String login, String password, String name, String surname, String email, String phone, RolesDTO role)
    {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    /**
     * Gets user id.
     *
     * @return id
     */
    public int getIdUser()
    {
        return idUser;
    }

    /**
     * Sets user id.
     *
     * @param idUser user id
     */
    public void setIdUser(int idUser)
    {
        this.idUser = idUser;
    }

    /**
     * Gets user login.
     *
     * @return login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * Sets user login.
     *
     * @param login user login
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * Gets user password.
     *
     * @return password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets user password.
     *
     * @param password user password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Gets user name.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets user name.
     *
     * @param name user name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets user surname.
     *
     * @return surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Sets user surname.
     *
     * @param surname user surname
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * Gets user email.
     *
     * @return email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets user email.
     *
     * @param email user email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Gets user phone.
     *
     * @return phone
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * Sets user phone.
     *
     * @param phone user phone
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * Gets user role.
     *
     * @return role
     */
    public RolesDTO getRole()
    {
        return role;
    }

    /**
     * Sets user role.
     *
     * @param role user role
     */
    public void setRole(RolesDTO role)
    {
        this.role = role;
    }

    /**
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "User: " + idUser + ", " + login + ", " + password + ", " + name + ", " + surname + ", " + email + ", " + phone + ", " + role;
    }
}
