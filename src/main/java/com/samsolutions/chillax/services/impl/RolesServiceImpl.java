package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.RolesDao;
import com.samsolutions.chillax.entity.Roles;
import com.samsolutions.chillax.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolesServiceImpl implements RolesService
{
    @Autowired
    private RolesDao rolesDao;

    public Roles getByRoleName(String role)
    {
        return rolesDao.getByName(role);
    }

    public List<Roles> getRoles()
    {
        return rolesDao.getAll();
    }

    public void addRole(Roles role)
    {
        rolesDao.create(role);
    }

    public void deleteRole(int idRole)
    {
        rolesDao.delete(idRole);
    }

    public void updateRole(Roles role)
    {
        rolesDao.update(role);
    }
}