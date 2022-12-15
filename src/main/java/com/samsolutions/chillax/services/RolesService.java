package com.samsolutions.chillax.services;

import com.samsolutions.chillax.entity.Roles;
import com.samsolutions.chillax.dao.RolesDao;

import java.util.List;

/**
 * This interface declares methods
 * for a service that works with Roles DAO methods and entities.
 *
 * @see RolesDao
 * @see Roles
 */
public interface RolesService
{
    /**
     * Gets role by role name.
     *
     * @param role role name
     * @return Roles
     */
    Roles getByRoleName(String role);

    /**
     * Gets all roles.
     *
     * @return List<Roles>
     */
    List<Roles> getRoles();

    /**
     * Creates a received role.
     *
     * @param role new role
     */
    void addRole(Roles role);

    /**
     * Deletes role by id.
     *
     * @param idRole role id
     */
    void deleteRole(int idRole);

    /**
     * Updates role.
     *
     * @param role modified role
     */
    void updateRole(Roles role);
}