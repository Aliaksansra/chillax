package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.PlaceConverterFromDTO;
import com.samsolutions.chillax.converters.PlaceConverterToDTO;
import com.samsolutions.chillax.dto.PlaceDTO;
import com.samsolutions.chillax.entity.Place;
import com.samsolutions.chillax.services.PlaceService;
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

public class PlaceFacadeImplTest
{

    @Mock
    private PlaceService placeService;

    @Mock
    private PlaceConverterToDTO converterToDTO;

    @Mock
    private PlaceConverterFromDTO converterFromDTO;

    @InjectMocks
    private PlaceFacadeImpl placeFacade;

    private static List<PlaceDTO> placeDTOList = new ArrayList<>();

    private static List<Place> placeList = new ArrayList<>();

    @BeforeAll
    public static void setUp()
    {
        placeDTOList.add(new PlaceDTO(1, "Минск", "ул.Притыцкого 62", "Re:public"));
        placeDTOList.add(new PlaceDTO(2, "Минск", "пр.Победителей 4", "Дворец спорта"));
        placeList.add(new Place(1, "Минск", "ул.Притыцкого 62", "Re:public"));
        placeList.add(new Place(2, "Минск", "пр.Победителей 4", "Дворец спорта"));
    }

    @BeforeEach
    public void set()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPlaces()
    {
        when(placeService.getPlaces()).thenReturn(placeList);
        when(converterToDTO.convert(placeList.get(0))).thenReturn(placeDTOList.get(0));
        when(converterToDTO.convert(placeList.get(1))).thenReturn(placeDTOList.get(1));
        List<PlaceDTO> actList = placeFacade.getPlaces();
        assertEquals(placeDTOList, actList);
        assertNotNull(actList);
    }

    @Test
    public void addNewPlace()
    {
        PlaceDTO placeDTO = placeDTOList.get(0);
        Place place = placeList.get(0);
        doNothing().when(placeService).addPlace(place);
        when(converterFromDTO.convert(placeDTO)).thenReturn(place);
        placeFacade.addNewPlace(placeDTO);
        verify(converterFromDTO).convert(placeDTO);
        verify(placeService).addPlace(place);
    }

    @Test
    public void placeUpdate()
    {
        PlaceDTO placeDTO = placeDTOList.get(0);
        Place place = placeList.get(0);
        doNothing().when(placeService).updatePlace(place);
        when(converterFromDTO.convert(placeDTO)).thenReturn(place);
        placeFacade.placeUpdate(placeDTO);
        verify(converterFromDTO).convert(placeDTO);
        verify(placeService).updatePlace(place);
    }

    @Test
    public void placeDelete()
    {
        doNothing().when(placeService).deletePlace(anyInt());
        placeFacade.placeDelete(1);
        verify(placeService).deletePlace(1);
    }
}