package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.UsersDTO;
import com.samsolutions.chillax.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming an users entities from a DTO.
 *
 * @see Converter
 * @see Users
 * @see UsersDTO
 */
@Component
public class UsersConverterFromDTO implements Converter<UsersDTO, Users>
{
    /**
     * Automatic injects of the RolesConverterFromDTO class
     * to convert the Roles entity from the RolesDTO required to create the Users entity.
     *
     * @see RolesConverterFromDTO
     */
    @Autowired
    private RolesConverterFromDTO rolesConverterFromDTO;

    /**
     * Overrides method 'convert', converts the @param UsersDTO
     * to the Users entity and returns it.
     *
     * @param usersDTO UsersDTO
     * @return Users
     */
    @Override
    public Users convert(UsersDTO usersDTO)
    {
        Users user = null;
        if(usersDTO != null)
        {
            user = new Users(usersDTO.getLogin(), usersDTO.getPassword(),
                    usersDTO.getName(), usersDTO.getSurname(), usersDTO.getEmail(),
                    usersDTO.getPhone(), rolesConverterFromDTO.convert(usersDTO.getRole()));
        }
        return user;
    }
}