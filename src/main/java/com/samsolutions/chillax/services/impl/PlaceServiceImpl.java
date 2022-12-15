package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.PlaceDao;
import com.samsolutions.chillax.entity.Place;
import com.samsolutions.chillax.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService
{

    @Autowired
    private PlaceDao placeDao;

    public List<Place> getPlaces()
    {
        return placeDao.getAll();
    }

    public void addPlace(Place place)
    {
        placeDao.create(place);
    }

    public void updatePlace(Place place)
    {
        placeDao.update(place);
    }

    public void deletePlace(int id)
    {
        placeDao.delete(id);
    }

    public Place getByPK(int id)
    {
        return placeDao.getByPK(id);
    }
}