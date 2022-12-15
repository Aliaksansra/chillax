package com.samsolutions.chillax.controllers;

import com.samsolutions.chillax.dto.RolesDTO;
import com.samsolutions.chillax.facades.RolesFacade;
import com.samsolutions.chillax.validators.RoleValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

/**
 * This RestController works with roles,
 * gets and sends them from and to the RolesFacade.
 *
 * @see RolesFacade
 */
@RestController
@RequestMapping("/api/admin/role/")
public class RolesController extends AbstractCRUDController<RolesDTO>
{
    /**
     * Automatic injects of the RolesFacade class that works with role entities and DTO.
     */
    @Autowired
    private RolesFacade rolesFacade;

    /**
     * Validates role data.
     */
    @Autowired
    private RoleValidator roleValidator;

    /**
     * Automatic injects of the RestErrorHandler class
     * to handle MethodArgumentNotValidException
     * that can be thrown if there are RolesDTO validation errors.
     *
     * @see RestErrorHandler
     */
    @Autowired
    private RestErrorHandler restErrorHandler;

    /**
     * Overrides method "add". Gets a new RolesDTO, checks it for validation errors.
     * If there aren`t errors, the RolesFacade will be called to create a new role in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     *
     * @param rolesDTO RolesDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> add(@Valid @RequestBody RolesDTO rolesDTO, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            rolesFacade.addNewRole(rolesDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Overrides method "delete". Gets role id and calls RolesFacade to delete role in the database by id.
     *
     * @param idRole role id
     * @return HttpStatus.OK
     */
    @Override
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public HttpStatus delete(@PathVariable("id") int idRole)
    {
        rolesFacade.deleteRole(idRole);
        return HttpStatus.OK;
    }

    /**
     * Overrides method "update". Gets a modified RolesDTO, checks it for validation errors.
     * If there aren`t errors, the RolesFacade will be called to update a modified role in the database.
     * If there are validation errors, they are returned to the front-end.
     *
     * @param rolesDTO RolesDTO
     * @param errors validation errors
     * @return ResponseEntity <List<FieldErrorDTO>, HttpStatus.BAD_REQUEST> - if there are validation errors
     * or ResponseEntity<>(0, HttpStatus.OK) - if not.
     */
    @Override
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@Valid @RequestBody RolesDTO rolesDTO, Errors errors)
    {
        if (errors.hasErrors())
        {
            return new ResponseEntity<>(restErrorHandler.processValidationError(errors), HttpStatus.BAD_REQUEST);
        } else
            {
            rolesFacade.updateRole(rolesDTO);
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }

    /**
     * Overrides method "initBinder".
     * Initializes the WebDataBinder that binds RoleValidator for role data validation.
     *
     * @see RoleValidator
     * @param binder WebDataBinder
     */
    @Override
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setValidator(roleValidator);
    }
}