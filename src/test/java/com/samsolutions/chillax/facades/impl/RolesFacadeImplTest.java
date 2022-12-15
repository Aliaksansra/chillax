package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.RolesConverterFromDTO;
import com.samsolutions.chillax.converters.RolesConverterToDTO;
import com.samsolutions.chillax.dto.RolesDTO;
import com.samsolutions.chillax.entity.Roles;
import com.samsolutions.chillax.services.RolesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RolesFacadeImplTest
{

    @Mock
    private RolesService rolesService;

    @Mock
    private RolesConverterToDTO converterToDTO;

    @Mock
    private RolesConverterFromDTO converterFromDTO;

    @InjectMocks
    private RolesFacadeImpl rolesFacade;

    private static List<RolesDTO> rolesDTOList = new ArrayList<>();

    private static List<Roles> rolesList = new ArrayList<>();

    @BeforeAll
    public static void setUp()
    {
        rolesDTOList.add(new RolesDTO(1, "admin"));
        rolesDTOList.add(new RolesDTO(2, "user"));
        rolesList.add(new Roles(1, "admin"));
        rolesList.add(new Roles(2, "user"));
    }

    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRoles()
    {
        when(rolesService.getRoles()).thenReturn(rolesList);
        when(converterToDTO.convert(rolesList.get(0))).thenReturn(rolesDTOList.get(0));
        when(converterToDTO.convert(rolesList.get(1))).thenReturn(rolesDTOList.get(1));
        List<RolesDTO> actList = rolesFacade.getRoles();
        assertEquals(rolesDTOList, actList);
        assertNotNull(actList);
    }

    @Test
    public void addNewRole()
    {
        RolesDTO rolesDTO = rolesDTOList.get(0);
        Roles role = rolesList.get(0);
        doNothing().when(rolesService).addRole(role);
        when(converterFromDTO.convert(rolesDTO)).thenReturn(role);
        rolesFacade.addNewRole(rolesDTO);
        verify(converterFromDTO).convert(rolesDTO);
        verify(rolesService).addRole(role);
    }

    @Test
    public void getByRoleName()
    {
        Roles role = rolesList.get(0);
        when(rolesService.getByRoleName(anyString())).thenReturn(role);
        when(converterToDTO.convert(role)).thenReturn(rolesDTOList.get(0));
        RolesDTO actRole = rolesFacade.getByRoleName("admin");
        assertNotNull(actRole);
        assertEquals(rolesDTOList.get(0), actRole);
    }

    @Test
    public void deleteRole()
    {
        doNothing().when(rolesService).deleteRole(anyInt());
        rolesFacade.deleteRole(1);
        verify(rolesService).deleteRole(1);
    }

    @Test
    public void updateRole()
    {
        RolesDTO rolesDTO = rolesDTOList.get(0);
        Roles role = rolesList.get(0);
        doNothing().when(rolesService).updateRole(role);
        when(converterFromDTO.convert(rolesDTO)).thenReturn(role);
        rolesFacade.updateRole(rolesDTO);
        verify(converterFromDTO).convert(rolesDTO);
        verify(rolesService).updateRole(role);
    }
}