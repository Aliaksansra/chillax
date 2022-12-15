package com.samsolutions.chillax.entity;

import java.io.Serializable;
import java.util.List;

/**
 * This class describes entity "Users"
 * according to fields of the table "USERS" in the database.
 */
public class Users implements Serializable
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
     *
     * @see Roles
     */
    private Roles role;

    /**
     * orders list
     *
     * @see Orders
     */
    private List<Orders> orders;

    /**
     * comments list
     *
     * @see Comments
     */
    private List<Comments> comments;

    /**
     * empty constructor
     */
    public Users()
    {
    }

    /**
     * constructor with parameters
     *
     * @param login user login
     * @param password user password
     * @param name user name
     * @param surname user surname
     * @param email user email
     * @param phone user phone
     * @param role user role
     */
    public Users(String login, String password, String name, String surname, String email, String phone, Roles role)
    {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.role = role;
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
    public Users(int idUser, String login, String password, String name, String surname, String email, String phone, Roles role)
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
    public Roles getRole()
    {
        return role;
    }

    /**
     * Sets user role.
     *
     * @param role user role
     */
    public void setRole(Roles role)
    {
        this.role = role;
    }

    /**
     * Gets list of orders.
     *
     * @return orders list
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     * Sets list orders.
     *
     * @param orders list of orders
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /**
     * Gets list of comments.
     *
     * @return comments list
     */
    public List<Comments> getComments() {
        return comments;
    }

    /**
     * Sets list of comments.
     *
     * @param comments list of comments
     */
    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    /**
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "User: " + idUser + ", " + login + ", " + password + ", " + name + ", " + surname + ", " + email + ", " + phone;
    }
}