package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.TypesConverterFromDTO;
import com.samsolutions.chillax.converters.TypesConverterToDTO;
import com.samsolutions.chillax.dto.TypesDTO;
import com.samsolutions.chillax.services.TypesService;
import com.samsolutions.chillax.entity.Types;
import com.samsolutions.chillax.facades.TypesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypesFacadeImpl implements TypesFacade
{
    @Autowired
    private TypesService typesService;

    @Autowired
    private TypesConverterToDTO converterToDTO;

    @Autowired
    private TypesConverterFromDTO converterFromDTO;

    public List<TypesDTO> getTypes()
    {
        List<Types> list = typesService.getTypes();
        List<TypesDTO> typesDTO = new ArrayList<TypesDTO>();
        for (Types type : list)
        {
            typesDTO.add(converterToDTO.convert(type));
        }
        return typesDTO;
    }

    public void addNewType(TypesDTO typesDTO)
    {
        typesService.addType(converterFromDTO.convert(typesDTO));
    }

    public void typeDelete(int id)
    {
        typesService.deleteType(id);
    }

    public void typeUpdate(TypesDTO typesDTO)
    {
        typesService.updateType(converterFromDTO.convert(typesDTO));
    }
}