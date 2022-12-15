package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.TypesDao;
import com.samsolutions.chillax.entity.Types;
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
public class TypesDaoImplTest
{

    @Autowired
    private TypesDao typesDao;

    private Types expType;

    @BeforeEach
    public void setUp()
    {
        expType = new Types(0, "Music");
        expType.setIdType(typesDao.create(expType));
    }

    @Test
    public void create()
    {
        Types actType = new Types(0, "Cinema");
        int id = typesDao.create(actType);
        actType.setIdType(id);
        assertEquals(typesDao.getByPK(id).toString(), actType.toString());
    }

    @Test
    public void getByPK()
    {
        Types actType = typesDao.getByPK(expType.getIdType());
        assertNotNull(actType);
        assertEquals(expType.toString(), actType.toString());
    }

    @Test
    public void getAll()
    {
        Types type2 = new Types(0, "Sport");
        typesDao.create(type2);
        List<Types> list = typesDao.getAll();
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void update()
    {
        int idType = expType.getIdType();
        Types typeUpdate = new Types(idType, "Theatre");
        typesDao.update(typeUpdate);
        Types actType = typesDao.getByPK(idType);
        assertNotEquals(expType.toString(), actType.toString());
        assertEquals(typeUpdate.toString(), actType.toString());
    }

    @Test
    public void delete()
    {
        int idType = expType.getIdType();
        typesDao.delete(idType);
        assertNull(typesDao.getByPK(idType));
    }
}