package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.PlaceDao;
import com.samsolutions.chillax.entity.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration("classpath:application-context-test.xml")
@ExtendWith(SpringExtension.class)
@Transactional
public class PlaceDaoImplTest
{

    @Autowired
    private PlaceDao placeDao;

    private Place expPlace;

    @BeforeEach
    public void setUp()
    {
        expPlace = new Place(0, "Минск", "пр.Победителей 4", "Дворец спорта");
        expPlace.setIdPlace(placeDao.create(expPlace));
    }

    @Test
    public void create()
    {
        Place actPlace = new Place(0, "Минск", "ул.Притыцкого 62", "Re:public");
        int id = placeDao.create(actPlace);
        actPlace.setIdPlace(id);
        assertEquals(placeDao.getByPK(id).toString(), actPlace.toString());
    }

    @Test
    public void getByPK()
    {
        Place actPlace = placeDao.getByPK(expPlace.getIdPlace());
        assertNotNull(actPlace);
        assertEquals(expPlace.toString(), actPlace.toString());
    }

    @Test
    public void getAll()
    {
        Place place2 = new Place(0, "Минск", "пл. Октябрьская 1-1", "Дворец Республики");
        placeDao.create(place2);
        List<Place> list = placeDao.getAll();
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void update()
    {
        int idPlace = expPlace.getIdPlace();
        Place placeUpdate = new Place(idPlace, "Минск", "ул.Октябрьская 5", "Концертный зал Минск");
        placeDao.update(placeUpdate);
        Place actPlace = placeDao.getByPK(idPlace);
        assertNotEquals(expPlace.toString(), actPlace.toString());
        assertEquals(placeUpdate.toString(), actPlace.toString());
    }

    @Test
    public void delete()
    {
        int idPlace = expPlace.getIdPlace();
        placeDao.delete(idPlace);
        assertNull(placeDao.getByPK(idPlace));
    }
}