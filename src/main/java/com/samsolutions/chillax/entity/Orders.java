package com.samsolutions.chillax.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * This class describes entity "Orders"
 * according to fields of the table "ORDERS" in the database.
 */
public class Orders implements Serializable
{
    /**
     * order id
     */
    private int idOrders;

    /**
     * order guid
     */
    private String guid;

    /**
     * order total price
     */
    private double totalPrice;

    /**
     * order discount
     */
    private int discount;

    /**
     * order date
     */
    private Date dateOfBooking;

    /**
     * order is paid or not
     */
    private boolean paid;

    /**
     * user whose order
     *
     * @see Users
     */
    private Users user;

    /**
     * order tickets
     *
     * @see Tickets
     */
    private List<Tickets> tickets;

    /**
     * empty constructor
     */
    public Orders()
    {
    }

    /**
     * constructor with parameters
     *
     * @param idOrders order id
     * @param guid order guid
     * @param totalPrice order price
     * @param discount order discount
     * @param dateOfBooking order date
     * @param paid order is paid or not
     * @param user user
     */
    public Orders(int idOrders, String guid, double totalPrice, int discount, Date dateOfBooking, boolean paid, Users user)
    {
        this.idOrders = idOrders;
        this.guid = guid;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.dateOfBooking = dateOfBooking;
        this.paid = paid;
        this.user = user;
    }

    /**
     * Gets order id.
     *
     * @return order id
     */
    public int getIdOrders()
    {
        return idOrders;
    }

    /**
     * Sets order id.
     *
     * @param idOrders order id
     */
    public void setIdOrders(int idOrders)
    {
        this.idOrders = idOrders;
    }

    /**
     * Gets order guid.
     *
     * @return order guid
     */
    public String getGuid()
    {
        return guid;
    }

    /**
     * Sets order guid.
     *
     * @param guid order guid
     */
    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    /**
     * Gets order price.
     *
     * @return price
     */
    public double getTotalPrice()
    {
        return totalPrice;
    }

    /**
     * Sets order price.
     *
     * @param totalPrice order price
     */
    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets order discount.
     *
     * @return discount
     */
    public int getDiscount()
    {
        return discount;
    }

    /**
     * Sets order discount.
     *
     * @param discount order discount
     */
    public void setDiscount(int discount)
    {
        this.discount = discount;
    }

    /**
     * Gets order date.
     *
     * @return date
     */
    public Date getDateOfBooking()
    {
        return dateOfBooking;
    }

    /**
     * Sets order date.
     *
     * @param dateOfBooking order date
     */
    public void setDateOfBooking(Date dateOfBooking)
    {
        this.dateOfBooking = dateOfBooking;
    }

    /**
     * Gets order is paid or not.
     *
     * @return paid
     */
    public boolean isPaid()
    {
        return paid;
    }

    /**
     * Sets order is paid or not
     *
     * @param paid order paid or not
     */
    public void setPaid(boolean paid)
    {
        this.paid = paid;
    }

    /**
     * Gets tickets list.
     *
     * @return tickets list
     */
    public List<Tickets> getTickets()
    {
        return tickets;
    }

    /**
     * Sets tickets list.
     *
     * @param tickets tickets list.
     */
    public void setTickets(List<Tickets> tickets)
    {
        this.tickets = tickets;
    }

    /**
     * Gets user.
     *
     * @return user
     */
    public Users getUser()
    {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user user
     */
    public void setUser(Users user)
    {
        this.user = user;
    }

    /**
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return "Order: " + idOrders + ", " + guid + ", " + totalPrice + ", " + discount + ", " + dateOfBooking + ", " + paid;
    }
}