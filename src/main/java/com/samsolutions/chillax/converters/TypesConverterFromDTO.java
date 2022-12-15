package com.samsolutions.chillax.converters;

import com.samsolutions.chillax.dto.TypesDTO;
import com.samsolutions.chillax.entity.Types;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class implements the Converter
 * for transforming a types entities from a DTO.
 *
 * @see Converter
 * @see Types
 * @see TypesDTO
 */
@Component
public class TypesConverterFromDTO implements Converter<TypesDTO, Types>
{
    /**
     * Overrides method 'convert', converts the @param TypesDTO
     * to the Types entity and returns it.
     *
     * @param typesDTO TypesDTO
     * @return Types
     */
    @Override
    public Types convert(TypesDTO typesDTO)
    {
        Types types = null;
        if(typesDTO != null)
        {
            types = new Types(typesDTO.getIdType(), typesDTO.getNameOfType());
        }
        return types;
    }
}