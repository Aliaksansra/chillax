package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.EventsDTO;
import com.samsolutions.chillax.facades.PlaceFacade;
import com.samsolutions.chillax.facades.TypesFacade;
import com.samsolutions.chillax.facades.RolesFacade;
import com.samsolutions.chillax.facades.EventsFacade;
import com.samsolutions.chillax.facades.OrderFacade;
import com.samsolutions.chillax.facades.UsersFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller returns different administration views.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController
{
    /**
     * Automatic injects of the PlaceFacade class that works with place entities and DTO.
     */
    @Autowired
    private PlaceFacade placeFacade;

    /**
     * Automatic injects of the TypesFacade class that works with type entities and DTO.
     */
    @Autowired
    private TypesFacade typesFacade;

    /**
     * Automatic injects of the RolesFacade class that works with role entities and DTO.
     */
    @Autowired
    private RolesFacade rolesFacade;

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
     * Automatic injects of the UsersFacade class that works with user entities and DTO.
     */
    @Autowired
    private UsersFacade usersFacade;

    /**
     * Calls PlaceFacade to get List<PlaceDTO>,
     * TypesFacade to get List<TypesDTO>.
     * Puts them and new EventsDTO in the Model attribute to fill in fields of the EventsDTO.
     * And returns page name.
     *
     * @see PlaceFacade
     * @see TypesFacade
     * @param model Model
     * @return "admin/addEvent" page addEvent.jsp located in the directory "admin"
     */
    @RequestMapping(method = RequestMethod.GET, value = "/event/new")
    public String sendNew(Model model)
    {
        model.addAttribute("event", new EventsDTO());
        model.addAttribute("place", placeFacade.getPlaces());
        model.addAttribute("type", typesFacade.getTypes());
        return "admin/addEvent";
    }

    /**
     * Calls TypesFacade to get List<TypesDTO>.
     * Puts it in the Model attribute and returns page name.
     *
     * @see TypesFacade
     * @param model Model
     * @return "admin/allTypes" page allTypes.jsp located in the directory "admin"
     */
    @RequestMapping(method = RequestMethod.GET, value = "/types")
    public String getAllTypes(Model model)
    {
        model.addAttribute("types", typesFacade.getTypes());
        return "admin/allTypes";
    }

    /**
     * Calls PlaceFacade to get List<PlaceDTO>.
     * Puts it in the Model attribute and returns page name.
     *
     * @see PlaceFacade
     * @param model Model
     * @return "admin/allPlaces" page allPlaces.jsp located in the directory "admin"
     */
    @RequestMapping(method = RequestMethod.GET, value = "/places")
    public String getAllPlaces(Model model)
    {
        model.addAttribute("places", placeFacade.getPlaces());
        return "admin/allPlaces";
    }

    /**
     * Calls RolesFacade to get List<RolesDTO>.
     * Puts it in the Model attribute and returns page name.
     *
     * @see RolesFacade
     * @param model Model
     * @return "admin/allRoles" page allRoles.jsp located in the directory "admin"
     */
    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    public String getAllRoles(Model model)
    {
        model.addAttribute("roles", rolesFacade.getRoles());
        return "admin/allRoles";
    }

    /**
     * Calls OrdersFacade to get paid or not list of all orders depending on @param isPaid.
     * Puts it in the Model attribute and returns page name.
     *
     * @see OrderFacade
     * @param model Model
     * @param isPaid true: paid, false: unpaid
     * @return "admin/allOrders" page allOrders.jsp located in the directory "admin"
     */
    @RequestMapping(method = RequestMethod.GET, value = "/orders/{isPaid}")
    public String getAllOrders(Model model, @PathVariable("isPaid") boolean isPaid)
    {
        model.addAttribute("orders", orderFacade.getAllOrders(isPaid));
        return "admin/allOrders";
    }

    /**
     * Returns page "admin".
     *
     * @return "admin" page admin.jsp
     */
    @RequestMapping(method = RequestMethod.GET)
    public String administration()
    {
        return "admin";
    }

    /**
     * Calls UsersFacade to get List<UsersDTO>, RolesFacade to get List<RolesDTO>.
     * Puts them in the Model attribute and returns page name.
     *
     * @see UsersFacade
     * @see RolesFacade
     * @param model Model
     * @return "admin/allUsers" page allUsers.jsp located in the directory "admin"
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public String getAllUsers(Model model)
    {
        model.addAttribute("users", usersFacade.getUsers());
        model.addAttribute("role", rolesFacade.getRoles());
        return "admin/allUsers";
    }

    /**
     * Calls EventsFacade to get EventsDTO by id, PlaceFacade to get List<PlaceDTO>,
     * TypesFacade to get List<TypesDTO>.
     * Puts them in the Model attribute for updating EventsDTO and returns page name.
     *
     * @see PlaceFacade
     * @see TypesFacade
     * @param model Model
     * @param id event id
     * @return "admin/updateEvent" page updateEvent.jsp located in the directory "admin"
     */
    @RequestMapping(value = "/event/update/{id}", method = RequestMethod.GET)
    public String sendCurrent(Model model,@PathVariable("id") int id)
    {
        model.addAttribute("event", eventsFacade.getEventByPK(id));
        model.addAttribute("place", placeFacade.getPlaces());
        model.addAttribute("type", typesFacade.getTypes());
        return "admin/updateEvent";
    }
}