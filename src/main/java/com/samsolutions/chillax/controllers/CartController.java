package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.CartEntryDTO;
import com.samsolutions.chillax.facades.OrderFacade;
import com.samsolutions.chillax.facades.CartFacade;
import com.samsolutions.chillax.facades.TicketsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * This RestController works with cart data using CartFacade class.
 * Gets and sends data from and to the CartFacade and
 * returns various json for working with cart.
 * Cart data is presented as a CartEntryDTO class and is stored in the HttpSession.
 *
 * @see CartFacade
 * @see HttpSession
 * @see CartEntryDTO
 */
@RestController
@RequestMapping("/api/cart")
public class CartController
{
    /**
     * Automatic injects of the CartFacade class
     * that works with cart data using CartEntryDTO class and HttpSession.
     */
    @Autowired
    private CartFacade cartFacade;

    /**
     * Automatic injects of the TicketsFacade class that works with ticket entities and DTO.
     */
    @Autowired
    private TicketsFacade ticketsFacade;

    /**
     * Automatic injects of the OrderFacade class that works with order entities and DTO.
     */
    @Autowired
    private OrderFacade orderFacade;

    /**
     * Gets cart data from CartFacade as HashMap<Events id, CartEntryDTO>
     * and returns it.
     *
     * @param session HttpSession
     * @return HashMap<Events id, CartEntryDTO>
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    HashMap<Integer, CartEntryDTO> getBasket(HttpSession session)
    {
        return cartFacade.getCart(session);
    }

    /**
     * Sends to the CartFacade selected event id and number of tickets to set
     * or update cart data in the current session.
     *
     * @param idEvent event id
     * @param countOfTickets number of tickets
     * @param session HttpSession
     * @return HttpStatus.OK - if the correct number of tickets is received or HttpStatus.BAD_REQUEST - if not
     */
    @RequestMapping(value = "/booking", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> booking(@RequestParam("idEvent") int idEvent, @RequestParam("countOfTickets") int countOfTickets, HttpSession session)
    {
        if(cartFacade.setCart(session, idEvent, countOfTickets)) {
            return new ResponseEntity<>(0, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Calls CartFacade to get cart data from current HttpSession.
     * Counts the total number of tickets in the basket and returns it.
     *
     * @param session HttpSession
     * @return total number of tickets
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody int getCountTickets(HttpSession session)
    {
        HashMap<Integer, CartEntryDTO> order = cartFacade.getCart(session);
        int countOfTickets = 0;
        if(order != null)
        {
            for (CartEntryDTO cartEntryDTO : order.values())
            {
                countOfTickets += cartEntryDTO.getCountOfTickets();
            }
        }
        return countOfTickets;
    }

    /**
     * Calls CartFacade to get cart data from current HttpSession.
     * Gets the number of remaining tickets from the request,
     * subtracts from it the number of tickets in the cart for the event
     * with the received id and returns result.
     *
     * @param idEvent event id
     * @param remainderOfTickets remaining number of tickets
     * @param session HttpSession
     * @return remaining number of tickets
     */
    @RequestMapping(value = "/tickets/remain", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody int getRemainingTickets(@RequestParam("idEvent") int idEvent, @RequestParam("remainderOfTickets") int remainderOfTickets, HttpSession session)
    {
        int ticketsInCart = 0;
        HashMap<Integer, CartEntryDTO> cart = cartFacade.getCart(session);
        if(cart != null && cart.containsKey(idEvent))
        {
            ticketsInCart = cart.get(idEvent).getCountOfTickets();
        }
        return remainderOfTickets - ticketsInCart;
    }

    /**
     * Calls TicketsFacade to get the number of remaining tickets for the event
     * with the received id and returns it.
     *
     * @see TicketsFacade
     * @param idEvent event id
     * @return remaining number of tickets
     */
    @RequestMapping(value = "/tickets/free", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody int getFreeTickets(@RequestParam("idEvent") int idEvent)
    {
        return ticketsFacade.getRemainderOfTickets(idEvent);
    }

    /**
     * Calls CartFacade to update cart data in the current HttpSession.
     *
     * @param cartEntryDTO dto with a new cart data
     * @param session HttpSession
     * @return ResponseEntity<>(total price of the current order, HttpStatus.OK) -
     * if the correct number of tickets is received or ResponseEntity<>(0, HttpStatus.BAD_REQUEST) - if not
     */
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody CartEntryDTO cartEntryDTO, HttpSession session)
    {
        if(ticketsFacade.getRemainderOfTickets(cartEntryDTO.getIdEvent()) >= cartEntryDTO.getCountOfTickets()) {
            return new ResponseEntity<>(cartFacade.updateCart(session, cartEntryDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Calls CartFacade to delete cart data from current session by received event id.
     *
     * @param idEvent event id
     * @param session HttpSession
     * @return HttpStatus.OK - if there are more orders in the basket
     * or HttpStatus.NOT_FOUND - if not
     */
    @RequestMapping(value = "/{idEvent}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    HttpStatus delete(@PathVariable int idEvent, HttpSession session)
    {
        if(cartFacade.deleteOrder(session, idEvent))
        {
            return HttpStatus.OK;
        }else
            {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Calls CartFacade to get all cart data from current session.
     * Generates an order from the received data: creates new order using OrdersFacade
     * and tickets using TicketsFacade.
     * Clears the HttpSession.
     *
     * @see OrderFacade
     * @see TicketsFacade
     * @param session HttpSession
     * @return new order guid
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String[] order(HttpSession session)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String[] guid = {orderFacade.createOrder(session, authentication.getName())};
        return guid;
    }

    /**
     * Calls OrderFacade to set value "true" to the field "paid"
     * of the order with received guid.
     *
     * @see OrderFacade
     * @param orderGuid order guid
     * @return HttpStatus.OK - if there are correct order guid
     * and login of the authorized user to whom this order belongs. Or HttpStatus.NOT_FOUND - if not.
     */
    @RequestMapping(value = "/pay", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> payment(@RequestParam("guid") String orderGuid) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (orderFacade.pay(orderGuid, auth.getName()))
        {
            return new ResponseEntity<>(0, HttpStatus.OK);
        } else
            {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }
}