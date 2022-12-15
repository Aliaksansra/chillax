package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.TypesDTO;
import com.samsolutions.chillax.entity.Types;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a types entities into a DTO.
 *
 * @see Converter
 * @see Types
 * @see TypesDTO
 */
@Component
public class TypesConverterToDTO implements Converter<Types, TypesDTO>
{
    /**
     * Overrides method 'convert', converts the @param Types
     * to the TypesDTO and returns it.
     *
     * @param types Types
     * @return TypesDTO
     */
    @Override
    public TypesDTO convert(Types types)
    {
        TypesDTO typesDTO = null;
        if(types != null)
        {
            typesDTO = new TypesDTO(types.getIdType(), types.getNameOfType());
        }
        return typesDTO;
    }
}