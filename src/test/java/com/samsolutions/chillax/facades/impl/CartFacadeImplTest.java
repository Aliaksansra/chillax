package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.dto.CartEntryDTO;
import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.facades.EventsFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartFacadeImplTest
{

    @Mock
    private EventsFacade eventsFacade;

    @Mock
    private HttpSession session;

    @Mock
    private CartEntryDTO cartEntryDTO;

    private static HashMap<Integer, CartEntryDTO> cart = new HashMap<>();

    @InjectMocks
    private CartFacadeImpl cartFacade;

    @BeforeAll
    public static void setUp()
    {
        cart.put(1, new CartEntryDTO(1, 10, "Brrit Floyd", Timestamp.valueOf("2018-11-24 19:30:00.000"), 100.0, null));
    }

    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setCart()
    {
        when(eventsFacade.getEventByPK(1)).thenReturn(new EventsDTO(1, "Динамо-Минск - СКА", "Хоккейный матч Динамо-Минск - СКА", Timestamp.valueOf("2019-01-04 19:30:00.000"), 7.00, 15000, "cka.jpg", null, null));
        when(session.getAttribute("map")).thenReturn(null);
        cartFacade.setCart(session, 1, 10);
        verify(session).getAttribute("map");
        assertNull(session.getAttribute("map"));
    }

    @Test
    public void getCart()
    {
        when(session.getAttribute("map")).thenReturn(cart);
        HashMap<Integer, CartEntryDTO> actCart = cartFacade.getCart(session);
        assertEquals(cart, actCart);
        assertNotNull(actCart);
    }

    @Test
    public void updateCart()
    {
        when(session.getAttribute("map")).thenReturn(cart);
        doNothing().when(session).removeAttribute("map");
        doNothing().when(session).setAttribute("map", cart);
        double actTotalPrice = cartFacade.updateCart(session, new CartEntryDTO(1, 10, "Brrit Floyd", Timestamp.valueOf("2018-11-24 19:30:00.000"), 100.0, null));
        verify(session).removeAttribute("map");
        verify(session).setAttribute("map", cart);
        assertTrue(actTotalPrice != 0);
        assertEquals(1000.0, actTotalPrice, 1);
    }

    @Test
    public void deleteOrder()
    {
        when(session.getAttribute("map")).thenReturn(cart);
        doNothing().when(session).removeAttribute("map");
        boolean result = cartFacade.deleteOrder(session, 1);
        verify(session).removeAttribute("map");
        assertFalse(result);
        cart.put(1, new CartEntryDTO(1, 10, "Brrit Floyd", Timestamp.valueOf("2018-11-24 19:30:00.000"), 100.0, null)); //return changes
    }
}