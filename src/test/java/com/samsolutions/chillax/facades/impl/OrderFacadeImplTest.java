package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.OrdersConverterFromDTO;
import com.samsolutions.chillax.converters.OrdersConverterToDTO;
import com.samsolutions.chillax.dto.CartEntryDTO;
import com.samsolutions.chillax.dto.OrdersDTO;
import com.samsolutions.chillax.entity.Orders;
import com.samsolutions.chillax.facades.CartFacade;
import com.samsolutions.chillax.services.OrdersService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

public class OrderFacadeImplTest
{

    @Mock
    private OrdersService ordersService;

    @Mock
    private CartFacade cartFacade;

    @Mock
    private OrdersConverterFromDTO converterFromDTO;

    @Mock
    private OrdersConverterToDTO converterToDTO;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private OrderFacadeImpl orderFacade;

    private static List<OrdersDTO> ordersDTOList = new ArrayList<>();

    private static List<Orders> ordersList = new ArrayList<>();

    @BeforeAll
    public static void setUp()
    {
        ordersDTOList.add(new OrdersDTO(1, "2a4a1567-a4a6-4ae6-9cc0-8e3094aae60e", 100, 0, Date.valueOf("2018-10-28"), false, 1));
        ordersDTOList.add(new OrdersDTO(2, "af2d7a1b-cb19-4736-b429-5cb73f5e672e", 50, 0, Date.valueOf("2018-11-11"), false, 1));
        ordersList.add(new Orders(1, "2a4a1567-a4a6-4ae6-9cc0-8e3094aae60e", 100, 0, Date.valueOf("2018-12-29"), false, null));
        ordersList.add(new Orders(2, "af2d7a1b-cb19-4736-b429-5cb73f5e672e", 50, 0, Date.valueOf("2018-11-11"), false, null));
    }

    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrder()
    {
        HashMap<Integer, CartEntryDTO> order = new HashMap<>();
        order.put(1, new CartEntryDTO(1, 1, "Brit Floyd", Timestamp.valueOf("2018-12-24 20:00:00.000"), 100, null));
        OrdersDTO newOrderDTO = ordersDTOList.get(0);
        Orders newOrder = ordersList.get(0);
        String guid2 = newOrder.getGuid();
        when(cartFacade.getCart(httpSession)).thenReturn(order);
        when(converterFromDTO.convert(newOrderDTO)).thenReturn(newOrder);
        when(ordersService.createOrder(anyObject(), eq(order), anyString())).thenReturn(guid2);
        doNothing().when(httpSession).removeAttribute("map");
        String guid = orderFacade.createOrder(httpSession, "admin");
        assertEquals(newOrderDTO.getGuid(), guid);
        assertNotNull(guid);
    }

    @Test
    public void getOrder()
    {
        Orders order = ordersList.get(0);
        when(ordersService.getOrder(anyString(), anyString())).thenReturn(order);
        when(converterToDTO.convert(ordersList.get(0))).thenReturn(ordersDTOList.get(0));
        OrdersDTO actOrder = orderFacade.getOrder("some guid", "some login");
        assertEquals(ordersDTOList.get(0), actOrder);
        assertNotNull(actOrder);
    }

    @Test
    public void pay()
    {
        when(ordersService.payOrder(anyString(), anyString())).thenReturn(true);
        boolean result = orderFacade.pay("some guid", "some login");
        assertTrue(result);
    }

    @Test
    public void getAllUnpaidOrders()
    {
        when(ordersService.getAllOrders()).thenReturn(ordersList);
        when(converterToDTO.convert(ordersList.get(0))).thenReturn(ordersDTOList.get(0));
        when(converterToDTO.convert(ordersList.get(1))).thenReturn(ordersDTOList.get(1));
        List<OrdersDTO> actList = orderFacade.getAllOrders(false);
        assertEquals(ordersDTOList, actList);
        assertNotNull(actList);
    }

    @Test
    public void getAllPaidOrders()
    {
        when(ordersService.getAllOrders()).thenReturn(ordersList);
        when(converterToDTO.convert(ordersList.get(0))).thenReturn(ordersDTOList.get(0));
        when(converterToDTO.convert(ordersList.get(1))).thenReturn(ordersDTOList.get(1));
        List<OrdersDTO> actList = orderFacade.getAllOrders(true);
        assertNotEquals(ordersDTOList, actList);
        assertTrue(actList.isEmpty());
    }

    @Test
    public void getAllUserUnpaidOrders()
    {
        when(ordersService.getAllByUser("admin")).thenReturn(ordersList);
        when(converterToDTO.convert(ordersList.get(0))).thenReturn(ordersDTOList.get(0));
        when(converterToDTO.convert(ordersList.get(1))).thenReturn(ordersDTOList.get(1));
        List<OrdersDTO> actList = orderFacade.getAllUserOrders("admin", false);
        assertEquals(ordersDTOList, actList);
        assertNotNull(actList);
    }

    @Test
    public void getAllUserPaidOrders()
    {
        when(ordersService.getAllByUser("admin")).thenReturn(ordersList);
        when(converterToDTO.convert(ordersList.get(0))).thenReturn(ordersDTOList.get(0));
        when(converterToDTO.convert(ordersList.get(1))).thenReturn(ordersDTOList.get(1));
        List<OrdersDTO> actList = orderFacade.getAllUserOrders("admin", true);
        assertNotEquals(ordersDTOList, actList);
        assertTrue(actList.isEmpty());
    }

    @Test
    public void getAllWrongUserOrders()
    {
        when(ordersService.getAllByUser("admin")).thenReturn(ordersList);
        when(converterToDTO.convert(ordersList.get(0))).thenReturn(ordersDTOList.get(0));
        when(converterToDTO.convert(ordersList.get(1))).thenReturn(ordersDTOList.get(1));
        List<OrdersDTO> actList = orderFacade.getAllUserOrders("wrong user", false);
        assertNotEquals(ordersDTOList, actList);
        assertTrue(actList.isEmpty());
    }

    @Test
    public void deleteOrder()
    {
        when(ordersService.deleteUserOrder(anyString(), anyString())).thenReturn(true);
        boolean result = orderFacade.deleteOrder("some guid", "user login");
        assertTrue(result);
    }

    @Test
    public void deleteUnpaidOrders()
    {
        doNothing().when(ordersService).deleteUnpaidOrders();
        orderFacade.deleteUnpaidOrders();
        verify(ordersService).deleteUnpaidOrders();
    }
}