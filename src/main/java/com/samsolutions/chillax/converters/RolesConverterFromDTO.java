package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.RolesDTO;
import com.samsolutions.chillax.entity.Roles;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a roles entities from a DTO.
 *
 * @see Converter
 * @see Roles
 * @see RolesDTO
 */
@Component
public class RolesConverterFromDTO implements Converter<RolesDTO, Roles>
{
    /**
     * Overrides method 'convert', converts the @param RolesDTO
     * to the Roles entity and returns it.
     *
     * @param rolesDTO RolesDTO
     * @return Roles
     */
    @Override
    public Roles convert(RolesDTO rolesDTO)
    {
        Roles role = null;
        if (rolesDTO != null)
        {
            role = new Roles(rolesDTO.getIdRole(), rolesDTO.getRole());
        }
        return role;
    }
}