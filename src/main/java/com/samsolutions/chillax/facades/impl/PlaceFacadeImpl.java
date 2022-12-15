package com.samsolutions.chillax.facades.impl;

import com.samsolutions.chillax.converters.PlaceConverterFromDTO;
import com.samsolutions.chillax.converters.PlaceConverterToDTO;
import com.samsolutions.chillax.dto.PlaceDTO;
import com.samsolutions.chillax.entity.Place;
import com.samsolutions.chillax.facades.PlaceFacade;
import com.samsolutions.chillax.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceFacadeImpl implements PlaceFacade
{
    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceConverterToDTO converterToDTO;

    @Autowired
    private PlaceConverterFromDTO converterFromDTO;

    public List<PlaceDTO> getPlaces()
    {
        List<Place> list = placeService.getPlaces();
        List<PlaceDTO> placeDTO = new ArrayList<PlaceDTO>();
        for (Place place : list)
        {
            placeDTO.add(converterToDTO.convert(place));
        }
        return placeDTO;
    }

    public void addNewPlace(PlaceDTO place)
    {
        placeService.addPlace(converterFromDTO.convert(place));
    }

    public void placeUpdate(PlaceDTO placeDTO)
    {
        placeService.updatePlace(converterFromDTO.convert(placeDTO));
    }

    public void placeDelete(int id)
    {
        placeService.deletePlace(id);
    }
}