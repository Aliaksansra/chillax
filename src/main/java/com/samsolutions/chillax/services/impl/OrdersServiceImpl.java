package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.EventsDao;
import com.samsolutions.chillax.dao.OrdersDao;
import com.samsolutions.chillax.dao.TicketsDao;
import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.dto.CartEntryDTO;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.entity.Tickets;
import com.samsolutions.chillax.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService
{
    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private TicketsDao ticketsDao;

    @Autowired
    private EventsDao eventsDao;

    @Autowired
    private UsersDao usersDao;

    public String createOrder(Orders newOrder, HashMap<Integer, CartEntryDTO> cart, String login)
    {
        newOrder.setUser(usersDao.getByLogin(login));
        newOrder.setIdOrders(ordersDao.create(newOrder));
        for (CartEntryDTO cartEntryDTO : cart.values())
        {
            for(int i = 0; i < cartEntryDTO.getCountOfTickets(); i++)
            {
                ticketsDao.create(new Tickets(0, UUID.randomUUID().toString(), false, eventsDao.getByPK(cartEntryDTO.getIdEvent()), newOrder));
            }
        }
       return newOrder.getGuid();
    }

    public Orders getOrder(String orderGuid, String login)
    {
        return ordersDao.getByGuid(orderGuid, login);
    }

    public boolean payOrder(String orderGuid, String login)
    {
        Orders order = ordersDao.getByGuid(orderGuid, login);
        if(order != null)
        {
            order.setPaid(true);
            ordersDao.update(order);
            return true;
        } else
            {
            return false;
        }

    }

    public List<Orders> getAllByUser(String login)
    {
        return ordersDao.getAllByUserPK(usersDao.getByLogin(login).getIdUser());
    }

    public List<Orders> getAllOrders()
    {
        return ordersDao.getAll();
    }

    public void deleteUnpaidOrders()
    {
        ordersDao.deleteAllUnpaid();
    }

    public boolean deleteUserOrder(String orderGuid, String login)
    {
       return ordersDao.delete(orderGuid, login);
    }
}