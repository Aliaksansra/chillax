package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.UsersDTO;
import com.samsolutions.chillax.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming an users entities into a DTO.
 *
 * @see Converter
 * @see Users
 * @see UsersDTO
 */
@Component
public class UsersConverterToDTO implements Converter<Users, UsersDTO>
{
    /**
     * Automatic injects of the RolesConverterToDTO class
     * to convert the Roles entity into the RolesDTO required to create the UsersDTO.
     *
     * @see RolesConverterToDTO
     */
    @Autowired
    private RolesConverterToDTO rolesConverterToDTO;

    /**
     * Overrides method 'convert', converts the @param Users
     * to the UsersDTO and returns it.
     *
     * @param users Users
     * @return UsersDTO
     */
    @Override
    public UsersDTO convert(Users users)
    {
        UsersDTO usersDTO = null;
        if(users != null)
        {
            usersDTO = new UsersDTO(users.getIdUser(), users.getLogin(), users.getPassword(),
                    users.getName(), users.getSurname(), users.getEmail(),
                    users.getPhone(), rolesConverterToDTO.convert(users.getRole()));
        }
        return usersDTO;
    }
}