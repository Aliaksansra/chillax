package com.samsolutions.chillax.facades;

import com.samsolutions.chillax.dto.OrdersDTO;
import com.samsolutions.chillax.converters.OrdersConverterFromDTO;
import com.samsolutions.chillax.converters.OrdersConverterToDTO;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.services.OrdersService;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This interface implements a pattern Facade.
 * Declares methods to manage the service and converters
 * that work with Orders and OrdersDTO classes.
 *
 * @see OrdersService
 * @see OrdersConverterToDTO
 * @see OrdersConverterFromDTO
 * @see OrdersDTO
 * @see Orders
 */
public interface OrderFacade
{
    /**
     * Converts received OrdersDTO into entities and calls service
     * for adding new order according to the user login.
     * Returns guid of the created order.
     *
     * @param session HttpSession
     * @param login user login
     * @return guid of the created order
     */
    String createOrder(HttpSession session, String login);

    /**
     * Calls service for getting order by guid and user login.
     * Converts received entity to DTO and returns it.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return OrdersDTO
     */
    OrdersDTO getOrder(String orderGuid, String login);

    /**
     * Calls service to set value "true" to the order field "paid"
     * according to the received guid and user login.
     *
     * @param orderGuid order guid
     * @param login user login
     * @return true - if order is paid or false - if not
     */
    boolean pay(String orderGuid, String login);

    /**
     * Gets all paid or not orders depending on @param isPaid.
     * Converts received entities to DTO and returns them.
     *
     * @param isPaid true: paid, false: unpaid
     * @return List<OrdersDTO>
     */
    List<OrdersDTO> getAllOrders(boolean isPaid);

    /**
     * Gets all paid or not user orders depending on @param "isPaid" by user login.
     * Converts received entities to DTO and returns them.
     *
     * @param login user login
     * @param isPaid true: paid, false: unpaid
     * @return List<OrdersDTO>
     */
    List<OrdersDTO> getAllUserOrders(String login, boolean isPaid);

    /**
     * Calls service for deleting order by id and user login.
     *
     * @param login user login
     * @param orderGuid order guid
     * @return true - if order is deleted, false - if not
     */
    boolean deleteOrder(String orderGuid, String login);

    /**
     * Calls service to delete unpaid orders every 2 days at midnight.
     *
     * @see Scheduled
     */
    @Scheduled(cron = "0 0 0 */2 * ?")
    void deleteUnpaidOrders();
}