package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.RolesConverterFromDTO;
import com.samsolutions.chillax.converters.RolesConverterToDTO;
import com.samsolutions.chillax.dto.RolesDTO;
import com.samsolutions.chillax.entity.Roles;
import com.samsolutions.chillax.services.RolesService;
import com.samsolutions.chillax.facades.RolesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RolesFacadeImpl implements RolesFacade
{
    @Autowired
    private RolesService rolesService;

    @Autowired
    private RolesConverterToDTO converterToDTO;

    @Autowired
    private RolesConverterFromDTO converterFromDTO;

    public List<RolesDTO> getRoles()
    {
        List<Roles> list = rolesService.getRoles();
        List<RolesDTO> rolesDTO = new ArrayList<>();
        for (Roles role : list)
        {
            rolesDTO.add(converterToDTO.convert(role));
        }
        return rolesDTO;
    }

    public void addNewRole(RolesDTO rolesDTO)
    {
        rolesService.addRole(converterFromDTO.convert(rolesDTO));
    }

    public RolesDTO getByRoleName(String role)
    {
        return converterToDTO.convert(rolesService.getByRoleName(role));
    }

    public void deleteRole(int idRole)
    {
        rolesService.deleteRole(idRole);
    }

    public void updateRole(RolesDTO rolesDTO)
    {
        rolesService.updateRole(converterFromDTO.convert(rolesDTO));
    }
}