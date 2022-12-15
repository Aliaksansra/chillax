package com.samsolutions.chillax.controllers;

import com.google.zxing.WriterException;
import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.dto.UsersDTO;
import com.samsolutions.chillax.dto.OrdersDTO;
import com.samsolutions.chillax.dto.TicketsDTO;
import com.samsolutions.chillax.dto.CommentsDTO;
import com.samsolutions.chillax.facades.CommentsFacade;
import com.samsolutions.chillax.facades.TypesFacade;
import com.samsolutions.chillax.facades.EventsFacade;
import com.samsolutions.chillax.facades.OrderFacade;
import com.samsolutions.chillax.facades.TicketsFacade;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * This controller returns different views including index.jsp on the first request.
 */
@Controller
public class MainController
{
    /**
     * Automatic injects of the CommentsFacade class that works with comment entities and DTO.
     */
    @Autowired
    private CommentsFacade commentsFacade;

    /**
     * Automatic injects of the TypesFacade class that works with type entities and DTO.
     */
    @Autowired
    private TypesFacade typesFacade;

    /**
     * Automatic injects of the EventsFacade class that works with event entities and DTO.
     */
    @Autowired
    private EventsFacade eventsFacade;

    /**
     * Automatic injects of the OrderFacade class that works with order entities and DTO.
     */
    @Autowired
    private OrderFacade orderFacade;

    /**
     * Automatic injects of the TicketsFacade class that works with ticket entities and DTO.
     */
    @Autowired
    private TicketsFacade ticketsFacade;

    /**
     * Gets logger for the MainController.class.
     *
     * @see Logger
     */
    private static final Logger logger = getLogger(MainController.class);

    /**
     * Returns page "cart".
     *
     * @return "cart" page cart.jsp
     */
    @RequestMapping(value = "/cart", method = RequestMethod.GET, produces = "application/json")
    public String basket()
    {
        return "cart";
    }

    /**
     * Gets List<EventsDTO> from EventsFacade according to the specified type, page and pageSize.
     * Puts it in the Model attribute and returns page name.
     *
     * @param model    Model
     * @param page     page number
     * @param idType   type id
     * @param pageSize maximum number of elements per page
     * @return "index" page index.jsp
     * @see EventsFacade
     * @see TypesFacade
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String allEvents(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "type", defaultValue = "0") int idType,
                            @RequestParam(value = "pageSize", defaultValue = "12") int pageSize)
    {
        List<EventsDTO> list = new ArrayList<>();
        if (idType == 0)
        {
            list = eventsFacade.getAllEvents();
            model.addAttribute("events", eventsFacade.getEventsDTO(page, pageSize));
        } else
            {
            list = eventsFacade.getAllEventsByType(idType);
            model.addAttribute("events", eventsFacade.getEventsDTOByType(page, pageSize, idType));
        }
        model.addAttribute("types", typesFacade.getTypes());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentType", idType);
        model.addAttribute("count", (Math.ceil((double)list.size() / pageSize) - 1));
        return "index";
    }

    /**
     * Logout. Redirects to the start page.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return "redirect:/index?logout" redirect to the index.jsp
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index?logout";
    }

    /**
     * Returns "403" page in case if access is denied.
     *
     * @return model with view name "403"
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied()
    {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) // check if user is login
        {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;
    }

    /**
     * Sends new user for registration.
     *
     * @param model Model
     * @return "registration" page registration.jsp
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model)
    {
        model.addAttribute("user", new UsersDTO());
        return "registration";
    }

    /**
     * Calls OrdersFacade to get order by received guid and authorized user login.
     * If the returned OrdersDTO from the OrderFacade is not null
     * puts it in the Model attribute and returns page name "payment".
     * Otherwise returns page name "403" about denied access due to invalid user login or order guid.
     *
     * @param model Model
     * @param orderGuid order guid
     * @return "payment" page payment.jsp if user login and order guid is correct
     * or "403" page 403.jsp if not
     * @see OrderFacade
     */
    @RequestMapping(method = RequestMethod.GET, value = "/payment/{guid}")
    public String payment(Model model, @PathVariable("guid") String orderGuid)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OrdersDTO orderDTO = orderFacade.getOrder(orderGuid, auth.getName());
        if (orderDTO != null)
        {
            model.addAttribute("order", orderDTO);
            return "payment";
        } else
            {
            return "403";
        }
    }

    /**
     *  Calls TicketsFacade to get List<TicketsDTO> by order guid and authorized user login.
     *  If the returned List<TicketsDTO> from the TicketsFacade is not null
     *  puts it in the Model attribute and returns page name "tickets".
     *  Otherwise returns page name "403" about denied access due to invalid user login or order guid.
     *
     * @param model Model
     * @param orderGuid order guid
     * @return "tickets" page tickets.jsp if user login and order guid is correct
     * or "403" page 403.jsp if not
     * @see TicketsFacade
     */
    @RequestMapping(method = RequestMethod.GET, value = "/orderedTickets/{guid}")
    public String getOrderedTickets(Model model, @PathVariable("guid") String orderGuid)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<TicketsDTO> tickets = ticketsFacade.getTicketsByOrder(orderGuid, auth.getName());
        if (tickets != null)
        {
            model.addAttribute("tickets", tickets);
            return "tickets";
        } else
            {
            return "403";
        }
    }

    /**
     * Calls TicketsFacade to create QRCode by ticket guid and user login
     * and returns created image like byte array.
     *
     * @param guid ticket guid
     * @return byte[]
     * @see TicketsFacade
     */
    @RequestMapping(method = RequestMethod.GET, value = "/qrCode", produces = "image/png")
    public @ResponseBody
    byte[] getQRCode(@RequestParam("guid") String guid)
    {
        byte[] image = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try (ByteArrayOutputStream output = new ByteArrayOutputStream())
        {
            BufferedImage bufferedImage = ticketsFacade.createQRCode(guid, auth.getName());
            if (bufferedImage != null)
            {
                ImageIO.write(bufferedImage, "PNG", output);
                image = output.toByteArray();
            }
        } catch (WriterException | IOException exception)
        {
            logger.error("Exception when writing QRCode", exception);
        }
        return image;
    }

    /**
     * Calls OrderFacade to get paid or unpaid user orders list depending on @param isPaid.
     *  If the returned List<OrdersDTO> is not null puts it in the Model attribute
     *  and returns page name "orders". Otherwise returns page name "403"
     *  about denied access due to invalid user login.
     *
     * @param model  Model
     * @param isPaid true: paid, false: unpaid
     * @return "orders" page orders.jsp if user login is correct or "403" page 403.jsp if not
     * @see OrderFacade
     */
    @RequestMapping(method = RequestMethod.GET, value = "/orders/{isPaid}")
    public String getOrders(Model model, @PathVariable("isPaid") boolean isPaid) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        if (!login.equals("guest")) {
            List<OrdersDTO> list = orderFacade.getAllUserOrders(login, isPaid);
            model.addAttribute("userOrders", list);
            model.addAttribute("paid", isPaid);
            return "orders";
        } else {
            return "403";
        }
    }

    /**
     * Calls EventsFacade to get EventsDTO by id,
     * CommentsFacade to get List<CommentsDTO> by event id,
     * TicketsFacade to get the number of remaining tickets for the event with the received id.
     * Puts them, user login and new comment in the Model attribute.
     * And returns page name.
     *
     * @param model Model
     * @param id event id
     * @return "event" page event.jsp
     * @see CommentsFacade
     * @see TicketsFacade
     */
    @RequestMapping(method = RequestMethod.GET, value = "/event/{id}", produces = "application/json")
    public String getEvent(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        model.addAttribute("event", eventsFacade.getEventByPK(id));
        model.addAttribute("comments", commentsFacade.getCommentsByEvent(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("remainderOfTickets", ticketsFacade.getRemainderOfTickets(id));
        model.addAttribute("login", auth.getName());
        model.addAttribute("comment", new CommentsDTO());
        return "event";
    }
}