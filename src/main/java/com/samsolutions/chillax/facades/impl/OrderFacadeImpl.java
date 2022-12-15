package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.OrdersConverterFromDTO;
import com.samsolutions.chillax.converters.OrdersConverterToDTO;
import com.samsolutions.chillax.dto.CartEntryDTO;
import com.samsolutions.chillax.dto.OrdersDTO;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.facades.CartFacade;
import com.samsolutions.chillax.facades.OrderFacade;
import com.samsolutions.chillax.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Component
public class OrderFacadeImpl implements OrderFacade
{
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CartFacade cartFacade;

    @Autowired
    private OrdersConverterFromDTO converterFromDTO;

    @Autowired
    private OrdersConverterToDTO converterToDTO;

    public String createOrder(HttpSession session, String login)
    {
        HashMap<Integer, CartEntryDTO> order = cartFacade.getCart(session);
        double totalPrice = 0;
        int countOfTickets = 0;
        for(CartEntryDTO cartEntryDTO : order.values())
        {
            totalPrice += cartEntryDTO.getPrice()*cartEntryDTO.getCountOfTickets();
            countOfTickets += cartEntryDTO.getCountOfTickets();
        }
        OrdersDTO newOrder = new OrdersDTO(0, UUID.randomUUID().toString(), totalPrice, 0, new Date(), false, 0);
        if(countOfTickets > 5)
        {
            newOrder.setDiscount(10);
            newOrder.setTotalPrice(newOrder.getTotalPrice() * 0.9);
        }
        session.removeAttribute("map");
        return ordersService.createOrder(converterFromDTO.convert(newOrder), order, login);
    }

    public OrdersDTO getOrder(String orderGuid, String login)
    {
        return converterToDTO.convert(ordersService.getOrder(orderGuid, login));
    }

    public boolean pay(String orderGuid, String login)
    {
        return ordersService.payOrder(orderGuid, login);
    }

    public List<OrdersDTO> getAllOrders(boolean isPaid)
    {
        List<OrdersDTO> ordersDTOS = new ArrayList<>();
        for (Orders order : ordersService.getAllOrders())
        {
            if (order.isPaid() == isPaid)
            {
                ordersDTOS.add(converterToDTO.convert(order));
            }
        }
        return ordersDTOS;
    }

    public List<OrdersDTO> getAllUserOrders(String login, boolean isPaid)
    {
    List<OrdersDTO> ordersDTOS = new ArrayList<>();
        List<Orders> orders = ordersService.getAllByUser(login);
        if(!orders.isEmpty())
        {
            for (Orders order : orders)
            {
                if (order.isPaid() == isPaid)
                {
                    ordersDTOS.add(converterToDTO.convert(order));
                }
            }
        }
        return ordersDTOS;
    }

    public boolean deleteOrder(String orderGuid, String login)
    {
        return ordersService.deleteUserOrder(orderGuid, login);
    }

    @Scheduled(cron = "0 0 0 */2 * ?")
    public void deleteUnpaidOrders()
    {
        ordersService.deleteUnpaidOrders();
    }
}