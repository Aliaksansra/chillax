package com.samsolutions.chillax.facades;

import com.samsolutions.chillax.dto.CartEntryDTO;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * This interface implements a pattern Facade.
 * Declares methods for working with CartEntryDTO class
 * and HttpSession that stores the cart data.
 *
 * @see CartEntryDTO
 * @see HttpSession
 */
public interface CartFacade
{
    /**
     * Creates new CartEntryDTO and puts it
     * in the current session as HashMap parameters if received count of tickets is correct.
     *
     * @param session Httpsession
     * @param idEvent id of selected event
     * @param countOfTickets number of tickets in the cart for the selected event
     * @return received count of tickets is correct or not
     */
    boolean setCart(HttpSession session, int idEvent, int countOfTickets);

    /**
     * Gets cart data from session as HashMap<Events id, CartEntryDTO>
     *
     * @param session Httpsession
     * @return HashMap<Integer, CartEntryDTO>
     */
    HashMap<Integer, CartEntryDTO> getCart(HttpSession session);

    /**
     * Updates cart data in the current session.
     *
     * @param session Httpsession
     * @param cartEntryDTO modified CartEntryDTO
     * @return total price of the current order
     */
    double updateCart(HttpSession session, CartEntryDTO cartEntryDTO);

    /**
     * Deletes CartEntryDTO from current session by received Events id.
     *
     * @param session Httpsession
     * @param idEvent event id.
     * @return there are more orders in the basket or not
     */
    boolean deleteOrder(HttpSession session, int idEvent);
}




