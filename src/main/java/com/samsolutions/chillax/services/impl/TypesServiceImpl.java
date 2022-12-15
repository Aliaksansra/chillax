package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.TypesDao;
import com.samsolutions.chillax.entity.Types;
import com.samsolutions.chillax.services.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypesServiceImpl implements TypesService
{
    @Autowired
    private TypesDao typesDao;

    public List<Types> getTypes()
    {
        return typesDao.getAll();
    }

    public void addType(Types type)
    {
        typesDao.create(type);
    }

    public void deleteType(int id)
    {
        typesDao.delete(id);
    }

    public void updateType(Types type)
    {
        typesDao.update(type);
    }

    public Types getByPK(int id)
    {
        return typesDao.getByPK(id);
    }
}