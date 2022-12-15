package com.samsolutions.chillax.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class describes fields of DTO orders.
 */
public class OrdersDTO implements Serializable
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
     * user id
     */
    private int idUser;

    /**
     * empty constructor
     */
    public OrdersDTO()
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
     * @param idUser user id
     */
    public OrdersDTO(int idOrders, String guid, double totalPrice, int discount, Date dateOfBooking, boolean paid, int idUser)
    {
        this.idOrders = idOrders;
        this.guid = guid;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.dateOfBooking = dateOfBooking;
        this.paid = paid;
        this.idUser = idUser;
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
     * Gets user id
     *
     * @return id
     */
    public int getIdUser()
    {
        return idUser;
    }

    /**
     * Sets user id
     *
     * @param idUser user id
     */
    public void setIdUser(int idUser)
    {
        this.idUser = idUser;
    }

    /**
     * Overrides method toString().
     *
     * @return String
     */
    @Override
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Order - ID: " + idOrders + ", guid: " + guid + ", total price: " + totalPrice + ", discount: " + discount + "%, date of booking: " + dateFormat.format(dateOfBooking) + ", paid: " + paid;
    }
}