package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.TypesConverterFromDTO;
import com.samsolutions.chillax.converters.TypesConverterToDTO;
import com.samsolutions.chillax.dto.TypesDTO;
import com.samsolutions.chillax.entity.Types;
import com.samsolutions.chillax.services.TypesService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TypesFacadeImplTest
{

    @Mock
    private TypesService typesService;

    @Mock
    private TypesConverterToDTO converterToDTO;

    @Mock
    private TypesConverterFromDTO converterFromDTO;

    @InjectMocks
    private TypesFacadeImpl typesFacade;

    private static List<TypesDTO> typesDTOList = new ArrayList<>();

    private static List<Types> typesList = new ArrayList<>();

    @BeforeAll
    public static void setUp()
    {
        typesDTOList.add(new TypesDTO(1, "Music"));
        typesDTOList.add(new TypesDTO(2, "Cinema"));
        typesList.add(new Types(1, "Music"));
        typesList.add(new Types(2, "Cinema"));
    }

    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTypes()
    {
        when(typesService.getTypes()).thenReturn(typesList);
        when(converterToDTO.convert(typesList.get(0))).thenReturn(typesDTOList.get(0));
        when(converterToDTO.convert(typesList.get(1))).thenReturn(typesDTOList.get(1));
        List<TypesDTO> actList = typesFacade.getTypes();
        assertEquals(typesDTOList, actList);
        assertNotNull(actList);
    }

    @Test
    public void addNewType()
    {
        TypesDTO typeDTO = typesDTOList.get(0);
        Types type = typesList.get(0);
        doNothing().when(typesService).addType(type);
        when(converterFromDTO.convert(typeDTO)).thenReturn(type);
        typesFacade.addNewType(typeDTO);
        verify(converterFromDTO).convert(typeDTO);
        verify(typesService).addType(type);
    }

    @Test
    public void typeDelete()
    {
        doNothing().when(typesService).deleteType(anyInt());
        typesFacade.typeDelete(1);
        verify(typesService).deleteType(1);
    }

    @Test
    public void typeUpdate()
    {
        TypesDTO typeDTO = typesDTOList.get(0);
        Types type = typesList.get(0);
        doNothing().when(typesService).updateType(type);
        when(converterFromDTO.convert(typeDTO)).thenReturn(type);
        typesFacade.typeUpdate(typeDTO);
        verify(converterFromDTO).convert(typeDTO);
        verify(typesService).updateType(type);
    }
}