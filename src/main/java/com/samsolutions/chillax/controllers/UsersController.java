package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.UsersDTO;
import com.samsolutions.chillax.facades.UsersFacade;
import com.samsolutions.chillax.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * This RestController works with users,
 * gets and sends them from and to the UsersFacade.
 *
 * @see UsersFacade
 */
@RestController
@RequestMapping("/api/user")
public class UsersController extends AbstractCRUDController<UsersDTO>
{
    /**
     * Automatic injects of the UsersFacade class that works with user entities and DTO.
     */
    @Autowired
    private UsersFacade usersFacade;

    /**
     * Validates user data.
     */
    @Autowired
    private UserValidator userValidator;

    /**
     * Automatic injects of the RestErrorHandler class
     * to handle MethodArgumentNotValidException
     * that can be thrown if there are UsersDTO validation errors.
     *
     * @see RestErrorHandler
     */
    @Autowired
    private RestErrorHandler restErrorHandler;

    /**
     * Overrides method "add". Gets a UsersDTO, checks it for validation errors.
     * If there aren`t errors, the UsersFacade will be called to create a new user in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param usersDTO UsersDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/admin/add", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> add(@Valid @RequestBody UsersDTO usersDTO, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            usersFacade.addUser(usersDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Overrides method "delete". Gets user id and calls UsersFacade to delete user in the database by id.
     *
     * @param idUser user id
     * @return HttpStatus.OK
     */
    @Override
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public HttpStatus delete(@PathVariable("id") int idUser)
    {
        usersFacade.userDelete(idUser);
        return HttpStatus.OK;
    }

    /**
     * Calls UsersFacade to get UsersDTO by user id and returns it.
     *
     * @param idUser user id
     * @return UsersDTO
     */
    @RequestMapping(value = "/admin/more", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    UsersDTO getUserInformation(@RequestParam("idUser") int idUser)
    {
        return usersFacade.getUsersDTOByPK(idUser);
    }

    /**
     * Gets a UsersDTO, checks it for validation errors. If there aren`t errors calls UsersFacade for
     * registration new user with role USER in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param usersDTO UsersDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<?> register(@Valid @RequestBody UsersDTO usersDTO, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            usersFacade.saveUser(usersDTO, "USER");
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Gets authenticated user by login and returns it.
     *
     * @return UsersDTO
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    UsersDTO getUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usersFacade.getUserDTOByLogin(auth.getName());
    }

    /**
     * Overrides method "initBinder".
     * Initializes the WebDataBinder that binds UserValidator for user data validation.
     *
     * @see UserValidator
     * @param binder WebDataBinder
     */
    @Override
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setValidator(userValidator);
    }
}