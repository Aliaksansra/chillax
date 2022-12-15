package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.facades.EventsFacade;
import com.samsolutions.chillax.facades.OrderFacade;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * This RestController works with orders,
 * gets and sends them from and to the OrderFacade.
 *
 * @see OrderFacade
 */
@RestController
@RequestMapping("/api/orders/")
public class OrdersController
{
    /**
     * Automatic injects of the EventsFacade class that works with event entities and DTO.
     */
    @Autowired
    private EventsFacade eventsFacade;

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
     * Calls EventsFacade to get the list of ordered events by received order guid
     * and authorized user login. And returns it.
     *
     * @see EventsFacade
     * @param orderGuid order guid
     * @return List<EventsDTO>
     */
    @RequestMapping(value = "/more", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EventsDTO> getOrderInformation(@RequestParam("guid") String orderGuid)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return eventsFacade.getOrderedEvents(orderGuid, auth.getName());
    }

    /**
     * Calls TicketsFacade to get tickets number by order guid, event id
     * and authorized user login. And returns it.
     *
     * @see TicketsFacade
     * @param orderGuid order guid
     * @param idEvent event id
     * @return tickets number
     */
    @RequestMapping(value = "/ticketscount", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    int getCountOfTickets(@RequestParam("guid") String orderGuid,@RequestParam("idEvent") int idEvent )
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ticketsFacade.getCountOfTickets(orderGuid, idEvent, auth.getName());
    }

    /**
     * Calls OrderFacade to delete order by received order guid and authorized user login.
     *
     * @see OrderFacade
     * @param orderGuid order guid
     * @return ResponseEntity with HttpStatus.OK if order is deleted
     * or with HttpStatus.BAD_REQUEST - if not
     */
    @RequestMapping(value = "/delete/{guid}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("guid") String orderGuid)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean result = orderFacade.deleteOrder(orderGuid, auth.getName());
        if(result){
            return new ResponseEntity<>(0, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }
}