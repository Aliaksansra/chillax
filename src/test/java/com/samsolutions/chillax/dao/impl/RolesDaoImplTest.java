package com.samsolutions.chillax.dao.impl;

import com.samsolutions.chillax.dao.RolesDao;
import com.samsolutions.chillax.entity.Roles;
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
public class RolesDaoImplTest
{

    @Autowired
    private RolesDao rolesDao;

    private Roles expRole;

    @BeforeEach
    public void setUp()
    {
        expRole = new Roles(0, "admin");
        expRole.setIdRole(rolesDao.create(expRole));
    }

    @Test
    public void getByPK()
    {
        Roles actRole = rolesDao.getByPK(expRole.getIdRole());
        assertNotNull(actRole);
        assertEquals(expRole.toString(), actRole.toString());
    }

    @Test
    public void getByName()
    {
        Roles actRole = rolesDao.getByName("admin");
        assertNotNull(actRole);
        assertEquals(expRole.toString(), actRole.toString());
    }

    @Test
    public void getAll()
    {
        Roles role2 = new Roles(0, "user");
        rolesDao.create(role2);
        List<Roles> list = rolesDao.getAll();
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void update()
    {
        int idRole = expRole.getIdRole();
        Roles roleUpdate = new Roles(idRole, "employee");
        rolesDao.update(roleUpdate);
        Roles actRole = rolesDao.getByPK(idRole);
        assertNotEquals(expRole.toString(), actRole.toString());
        assertEquals(roleUpdate.toString(), actRole.toString());
    }

    @Test
    public void delete()
    {
        int idRole = expRole.getIdRole();
        rolesDao.delete(idRole);
        assertNull(rolesDao.getByPK(idRole));
    }

    @Test
    public void create()
    {
        Roles actRole = new Roles(0, "client");
        int id = rolesDao.create(actRole);
        actRole.setIdRole(id);
        assertEquals(rolesDao.getByPK(id).toString(), actRole.toString());
    }
}