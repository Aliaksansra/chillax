package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.OrdersDao;
import com.samsolutions.chillax.dao.UsersDao;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration("classpath:application-context-test.xml")
@ExtendWith(SpringExtension.class)
@Transactional
public class OrdersDaoImplTest
{

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private UsersDao usersDao;

    private Users user;

    private List<Orders> ordersList = new ArrayList<>();

    @BeforeEach
    public void setUp()
    {
        user = new Users(0, "root", "root", "Luke", "Skywalker", "email@gmail.com", "+375296666666", null);
        int userId = usersDao.create(user);
        user.setIdUser(userId);
        Orders order1 = new Orders(0, "2a4a1567-a4a6-4ae6-9cc0-8e3094aae60e", 100, 10, Date.valueOf("2018-10-28"), false, user);
        Orders order2 = new Orders(0, "af2d7a1b-cb19-4736-b429-5cb73f5e672e", 50, 0, Date.valueOf("2018-11-11"), true, user);
        order1.setIdOrders(ordersDao.create(order1));
        order2.setIdOrders(ordersDao.create(order2));
        ordersList.add(order1);
        ordersList.add(order2);
    }

    @Test
    public void create()
    {
        Orders actOrder = new Orders(0, "5edf93be-2b57-440d-8152-e08714bbeddb", 30, 0, Date.valueOf("2018-11-20"), false, user);
        int id = ordersDao.create(actOrder);
        actOrder.setIdOrders(id);
        assertEquals(ordersDao.getByPK(id, "root").toString(), actOrder.toString());
    }

    @Test
    public void getByPK()
    {
        Orders expOrder = ordersList.get(0);
        Orders actOrder = ordersDao.getByPK(expOrder.getIdOrders(), "root");
        assertNotNull(actOrder);
        assertEquals(expOrder.toString(), actOrder.toString());
        assertNotEquals(ordersList.get(1).toString(), actOrder.toString());
    }

    @Test
    public void getAll()
    {
        List<Orders> list = ordersDao.getAll();
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(ordersList, list);
    }

    @Test
    public void update()
    {
        int idOrder = ordersList.get(0).getIdOrders();
        Orders orderUpdate = new Orders(idOrder, "671d9975-90b3-42c6-bfba-8e784e060aa0", 40, 0, Date.valueOf("2018-10-11"), true, user);
        ordersDao.update(orderUpdate);
        Orders actOrder = ordersDao.getByPK(idOrder, "root");
        assertNotEquals(ordersList.get(0).toString(), actOrder.toString());
        assertEquals(orderUpdate.toString(), actOrder.toString());
    }

    @Test
    public void delete()
    {
        Orders order = ordersList.get(0);
        ordersDao.delete(order.getGuid(), "root");
        assertNull(ordersDao.getByPK(order.getIdOrders(), "root"));
    }

    @Test
    public void getAllByUserPK()
    {
        List<Orders> list = ordersDao.getAllByUserPK(user.getIdUser());
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(ordersList, list);
    }

    @Test
    public void deleteAllUnpaid()
    {
        ordersDao.deleteAllUnpaid();
        assertNull(ordersDao.getByPK(ordersList.get(0).getIdOrders(), "root"));
        assertNotNull(ordersDao.getByPK(ordersList.get(1).getIdOrders(), "root"));
    }

    @Test
    public void getByGuid()
    {
        Orders expOrder = ordersList.get(0);
        Orders actOrder = ordersDao.getByGuid(expOrder.getGuid(), "root");
        assertNotNull(actOrder);
        assertEquals(expOrder.toString(), actOrder.toString());
        assertNotEquals(ordersList.get(1).toString(), actOrder.toString());
    }
}