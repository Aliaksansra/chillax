package com.samsolutions.chillax.facades;

import com.samsolutions.chillax.dto.RolesDTO;
import com.samsolutions.chillax.entity.Roles;
import com.samsolutions.chillax.converters.RolesConverterFromDTO;
import com.samsolutions.chillax.converters.RolesConverterToDTO;
import com.samsolutions.chillax.services.RolesService;

import java.util.List;

/**
 * This interface implements a pattern Facade.
 * Declares methods to manage the service and converters
 * that work with Roles and RolesDTO classes.
 *
 * @see RolesService
 * @see RolesConverterFromDTO
 * @see RolesConverterToDTO
 * @see RolesDTO
 * @see Roles
 */
public interface RolesFacade
{
    /**
     * Gets all roles.
     * Converts received entities to DTO and returns them.
     *
     * @return List<RolesDTO>
     */
    List<RolesDTO> getRoles();

    /**
     * Converts received DTO into entities and calls service to create new role.
     *
     * @param rolesDTO new role
     */
    void addNewRole(RolesDTO rolesDTO);

    /**
     * Gets role by name.
     * Converts received entity to DTO and returns it.
     *
     * @param role role name
     * @return RolesDTO
     */
    RolesDTO getByRoleName(String role);

    /**
     * Calls service to delete role by id.
     *
     * @param idRole role id
     */
    void deleteRole(int idRole);

    /**
     * Converts received DTO into entities and calls service to update role.
     *
     * @param rolesDTO modified role
     */
    void updateRole(RolesDTO rolesDTO);
}