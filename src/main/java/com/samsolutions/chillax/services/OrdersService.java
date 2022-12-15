package com.samsolutions.chillax.services;

import com.samsolutions.chillax.dto.CartEntryDTO;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.dao.OrdersDao;

import java.util.HashMap;
import java.util.List;

/**
 * This interface declares methods
 * for a service that works with Orders DAO methods and entities.
 *
 * @see OrdersDao
 * @see Orders
 */
public interface OrdersService
{
    /**
     * Creates a received order according to the user login.
     *
     * @param newOrder new order
     * @param cart HashMap<Integer, CartEntryDTO>
     * @param login user login
     * @return guid of the created order
     */
    String createOrder(Orders newOrder, HashMap<Integer, CartEntryDTO> cart, String login);

    /**
     * Gets order by guid and user login.
     *
     * @param orderGuid Orders guid
     * @param login user login
     * @return Orders
     */
    Orders getOrder(String orderGuid, String login);

    /**
     * Sets value true to the order field "paid" by guid and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return true - if order is paid or false - if not
     */
    boolean payOrder(String orderGuid, String login);

    /**
     * Gets all user orders by user login.
     *
     * @param login user login
     * @return List<Orders>
     */
    List<Orders> getAllByUser(String login);

    /**
     * Gets all orders.
     *
     * @return List<Orders>
     */
    List<Orders> getAllOrders();

    /**
     * Deletes all unpaid orders.
    */
    void deleteUnpaidOrders();

    /**
     *
     * Deletes order by guid and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return true - if order is deleted or false - if not
     */
    boolean deleteUserOrder(String orderGuid, String login);
}