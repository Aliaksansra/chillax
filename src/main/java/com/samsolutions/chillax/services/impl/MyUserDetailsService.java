package com.samsolutions.chillax.services.impl;

import com.samsolutions.chillax.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This service implements UserDetailsService,
 * works with users DAO and returns UserDetails for authorization.
 *
 * @see UserDetailsService
 */
@Service(value = "detailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService
{
    /**
     * automatic injects of UsersDao class
     * to get the user by login.
     *
     * @see UsersDao
     */
    @Autowired
    private UsersDao usersDao;

    /**
     * Overrides method 'loadUserByUsername',
     * Models core user information retrieved by the UserDetailsService.
     *
     * @param login user login
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        com.samsolutions.chillax.entity.Users user = usersDao.getByLogin(login);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ user.getRole().getRole()));
        User userAuth = new User(user.getLogin(), user.getPassword(), true, true, true, true, authorities);
        return userAuth;
    }
}