package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.dto.CartEntryDTO;
import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.facades.CartFacade;
import com.samsolutions.chillax.facades.EventsFacade;
import com.samsolutions.chillax.facades.TicketsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Component
public class CartFacadeImpl implements CartFacade
{
    @Autowired
    private EventsFacade eventsFacade;

    @Autowired
    private TicketsFacade ticketsFacade;

    public boolean setCart(HttpSession session, int idEvent, int countOfTickets)
    {
        boolean isCorrectTicketsCount = false;
        int ticketsRemainder = ticketsFacade.getRemainderOfTickets(idEvent);
        if(ticketsRemainder >= countOfTickets) {
            isCorrectTicketsCount = true;
            EventsDTO event = eventsFacade.getEventByPK(idEvent);
            CartEntryDTO newCart = new CartEntryDTO(idEvent, countOfTickets, event.getTitle(), event.getDatetimeOfEvent(), event.getPrice(), event.getPlace());
            HashMap<Integer, CartEntryDTO> carts;
            if (session.getAttribute("map") == null) {
                carts = new HashMap<>();
                carts.put(idEvent, newCart);
            } else {
                carts = (HashMap<Integer, CartEntryDTO>) session.getAttribute("map");
                if (carts.containsKey(idEvent)) {
                    CartEntryDTO cartEntryDTO = carts.get(idEvent);
                    if(ticketsRemainder >= (cartEntryDTO.getCountOfTickets() + countOfTickets)) {
                        cartEntryDTO.setCountOfTickets(countOfTickets + cartEntryDTO.getCountOfTickets());
                        carts.put(idEvent, cartEntryDTO);
                    } else {
                        isCorrectTicketsCount = false;
                    }
                } else {
                    carts.put(idEvent, newCart);
                }
                session.removeAttribute("map");
            }
            session.setAttribute("map", carts);
        }
        return isCorrectTicketsCount;
    }

    public HashMap<Integer, CartEntryDTO> getCart(HttpSession session)
    {
        return (HashMap<Integer, CartEntryDTO>) session.getAttribute("map");
    }

    public double updateCart(HttpSession session, CartEntryDTO cart)
    {
        HashMap<Integer, CartEntryDTO> carts = (HashMap<Integer, CartEntryDTO>) session.getAttribute("map");
        CartEntryDTO cartEntryDTO = carts.get(cart.getIdEvent());
        cartEntryDTO.setCountOfTickets(cart.getCountOfTickets());
        carts.put(cart.getIdEvent(), cartEntryDTO);
        session.removeAttribute("map");
        session.setAttribute("map", carts);
        return cartEntryDTO.getCountOfTickets() * cartEntryDTO.getPrice();
    }

    public boolean deleteOrder(HttpSession session, int idEvent)
    {
        HashMap<Integer, CartEntryDTO> carts = (HashMap<Integer, CartEntryDTO>) session.getAttribute("map");
        carts.remove(idEvent);
        session.removeAttribute("map");
        if (carts.isEmpty())
        {
            return false;
        } else
            {
            session.setAttribute("map", carts);
            return true;
        }
    }
}