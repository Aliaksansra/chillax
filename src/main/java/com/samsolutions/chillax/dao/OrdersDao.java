package com.samsolutions.chillax.dao;

import com.samsolutions.chillax.entity.Orders;
import java.util.List;
import com.samsolutions.chillax.entity.Users;

/**
 * This interface declares methods of CRUD operations
 * to work with entities "Orders" and transactions to the database.
 *
 * @see Orders
 */
public interface OrdersDao
{
    /**
     * Creates new transmitted order in the database.
     *
     * @param orders new order
     * @return id of the created order
     */
    int create(Orders orders);

    /**
     * Gets order from the database by primary key and user login.
     *
     * @param key primary key
     * @param login user login
     * @return Orders
     */
    Orders getByPK(int key, String login);

    /**
     * Updates order in the database.
     *
     * @param orders modified order
     */
    void update(Orders orders);

    /**
     * Deletes order from the database by primary key and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return true - if order is deleted, false - if not
     */
    boolean delete(String orderGuid, String login);

    /**
     * Gets all orders from the database.
     *
     * @return List<Orders>
     */
    List<Orders> getAll();

    /**
     * Gets all orders from the database by user id.
     *
     * @param id user id
     * @return List<Orders>
     * @see Users
     */
    List<Orders> getAllByUserPK(int id);

    /**
     * Deletes unpaid orders from the database.
     */
    void deleteAllUnpaid();

    /**
     * Gets order from the database by guid and user login.
     *
     * @param guid order guid
     * @param login user login
     * @return Orders
     */
    Orders getByGuid(String guid, String login);

}