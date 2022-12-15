package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.dto.UsersDTO;
import com.samsolutions.chillax.entity.Users;
import com.samsolutions.chillax.facades.UsersFacade;
import com.samsolutions.chillax.converters.UsersConverterFromDTO;
import com.samsolutions.chillax.converters.UsersConverterToDTO;
import com.samsolutions.chillax.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersFacadeImpl implements UsersFacade
{
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersConverterFromDTO converterFromDTO;

    @Autowired
    private UsersConverterToDTO converterToDTO;

    /**
     * Encodes password in BCrypt code.
     * @param password user password
     * @return coded password
     * @see BCryptPasswordEncoder
     */
    private String encoder(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        return passwordEncoder.encode(password);
    }

    public void saveUser(UsersDTO usersDTO, String role)
    {
        String password = usersDTO.getPassword();
        usersDTO.setPassword(encoder(password));
        usersService.saveNewUser(converterFromDTO.convert(usersDTO), role);
    }

    public boolean checkUniqueUser(UsersDTO userDTO, String field)
    {
        Users user = converterFromDTO.convert(userDTO);
        Users userResult;
        switch (field)
        {
            case "login":
                userResult = usersService.getByLogin(user.getLogin());
                break;
            case "email":
                userResult = usersService.getByEmail(user.getEmail());
                break;
            default:
                userResult = new Users(); //if such cases don`t work, function will return "false";
        }
        return userResult == null; //true - database return null so this user has unique fields
    }

    public List<UsersDTO> getUsers()
    {
        List<Users> list = usersService.getAllUsers();
        List<UsersDTO> usersDTO = new ArrayList<>();
        for (Users user : list)
        {
            usersDTO.add(converterToDTO.convert(user));
        }
        return usersDTO;
    }

    public void userDelete (int id)
    {
        usersService.deleteUser(id);
    }

    public void addUser(UsersDTO usersDTO)
    {
        String password = usersDTO.getPassword();
        usersDTO.setPassword(encoder(password));
        usersService.registerUser(converterFromDTO.convert(usersDTO));
    }

    public Users getUserByLogin(String login)
    {
       return usersService.getByLogin(login);
    }

    public Users getUserByPK(int id)
    {
       return usersService.getUserByPK(id);
    }

    public UsersDTO getUsersDTOByPK(int idUser)
    {
        return converterToDTO.convert(usersService.getUserByPK(idUser));
    }

    public UsersDTO getUserDTOByLogin(String login)
    {
        return converterToDTO.convert(usersService.getByLogin(login));
    }
}