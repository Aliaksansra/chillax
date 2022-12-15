package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.UsersConverterFromDTO;
import com.samsolutions.chillax.converters.UsersConverterToDTO;
import com.samsolutions.chillax.dto.UsersDTO;
import com.samsolutions.chillax.entity.Users;
import com.samsolutions.chillax.services.UsersService;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersFacadeImplTest
{

    @Mock
    private UsersService usersService;

    @Mock
    private UsersConverterFromDTO converterFromDTO;

    @Mock
    private UsersConverterToDTO converterToDTO;

    @InjectMocks
    private UsersFacadeImpl usersFacade;

    private static List<Users> usersList = new ArrayList<>();

    private static List<UsersDTO> usersDTOList = new ArrayList<>();

    @BeforeAll
    public static void setUp()
    {
        usersList.add(new Users(1, "root", "root", "Luke", "Skywalker", "email@gmail.com", "+375296666666", null));
        usersList.add(new Users(2, "qwerty", "123", "Leonard", "Nimoy", "volcano@gmail.com", "+78447474745", null));
        usersDTOList.add(new UsersDTO(1, "root", "root", "Luke", "Skywalker", "email@gmail.com", "+375296666666", null));
        usersDTOList.add(new UsersDTO(2, "qwerty", "123", "Leonard", "Nimoy", "volcano@gmail.com", "+78447474745", null));
    }

    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveUser()
    {
        UsersDTO newUserDTO = usersDTOList.get(0);
        Users user = usersList.get(0);
        when(converterFromDTO.convert(newUserDTO)).thenReturn(user);
        doNothing().when(usersService).saveNewUser(user, "user");
        usersFacade.saveUser(newUserDTO, "user");
        verify(usersService).saveNewUser(user, "user");
    }

    @Test
    public void checkNotUniqueUser()
    {
        Users user = usersList.get(0);
        when(converterFromDTO.convert(usersDTOList.get(0))).thenReturn(user);
        when(usersService.getByLogin("root")).thenReturn(user);
        boolean result = usersFacade.checkUniqueUser(usersDTOList.get(0), "login");
        assertFalse(result);
    }

    @Test
    public void checkUniqueUser()
    {
        Users user = usersList.get(0);
        when(converterFromDTO.convert(usersDTOList.get(0))).thenReturn(user);
        when(usersService.getByEmail("email@gmail.com")).thenReturn(null);
        boolean result = usersFacade.checkUniqueUser(usersDTOList.get(0), "email");
        assertTrue(result);
    }

    @Test
    public void getUsers()
    {
        when(usersService.getAllUsers()).thenReturn(usersList);
        when(converterToDTO.convert(usersList.get(0))).thenReturn(usersDTOList.get(0));
        when(converterToDTO.convert(usersList.get(1))).thenReturn(usersDTOList.get(1));
        List<UsersDTO> actList = usersFacade.getUsers();
        assertNotNull(actList);
        assertEquals(usersDTOList, actList);
    }

    @Test
    public void addUser()
    {
        Users newUser = usersList.get(0);
        doNothing().when(usersService).registerUser(newUser);
        when(converterFromDTO.convert(usersDTOList.get(0))).thenReturn(newUser);
        usersFacade.addUser(usersDTOList.get(0));
        verify(usersService).registerUser(newUser);
    }

    @Test
    public void getUserByLogin()
    {
        when(usersService.getByLogin("root")).thenReturn(usersList.get(0));
        Users actUser = usersFacade.getUserByLogin("root");
        assertNotNull(actUser);
        assertEquals(usersList.get(0), actUser);
    }

    @Test
    public void getUserByPK()
    {
        when(usersService.getUserByPK(1)).thenReturn(usersList.get(0));
        Users actUser = usersFacade.getUserByPK(1);
        assertNotNull(actUser);
        assertEquals(usersList.get(0), actUser);
    }

    @Test
    public void userDelete()
    {
        doNothing().when(usersService).deleteUser(1);
        usersFacade.userDelete(1);
        verify(usersService).deleteUser(1);
    }

    @Test
    public void getUsersDTOByPK()
    {
        Users user = usersList.get(0);
        when(usersService.getUserByPK(1)).thenReturn(user);
        when(converterToDTO.convert(user)).thenReturn(usersDTOList.get(0));
        UsersDTO actUser = usersFacade.getUsersDTOByPK(1);
        assertNotNull(actUser);
        assertEquals(usersDTOList.get(0), actUser);
    }

    @Test
    public void getUserDTOByLogin()
    {
        Users user = usersList.get(0);
        when(usersService.getByLogin("root")).thenReturn(user);
        when(converterToDTO.convert(user)).thenReturn(usersDTOList.get(0));
        UsersDTO usersDTO = usersFacade.getUserDTOByLogin("root");
        assertNotNull(usersDTO);
        assertEquals(usersDTOList.get(0), usersDTO);
    }
}