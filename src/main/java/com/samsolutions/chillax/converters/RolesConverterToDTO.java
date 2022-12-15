package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.RolesDTO;
import com.samsolutions.chillax.entity.Roles;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a roles entities into a DTO.
 *
 * @see Converter
 * @see Roles
 * @see RolesDTO
 */
@Component
public class RolesConverterToDTO implements Converter<Roles, RolesDTO>
{
    /**
     * Overrides method 'convert', converts the @param Roles
     * to the RolesDTO and returns it.
     *
     * @param roles Roles
     * @return RolesDTO
     */
    @Override
    public RolesDTO convert(Roles roles)
    {
        RolesDTO rolesDTO = null;
        if(roles != null)
        {
            rolesDTO = new RolesDTO(roles.getIdRole(), roles.getRole());
        }
        return rolesDTO;
    }
}