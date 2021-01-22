package com.webshop.app.service;

import com.webshop.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import javax.transaction.Transactional;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userService.findUserByUserName(userName);
    return  buildUserForAuthentication(user);

    }

    private UserDetails buildUserForAuthentication(User user){
        return new org.springframework.security.core.userdetails.User(
         user.getUserName(), user.getPassword(), user.getActive(), true,true,true, null);


    }

}
