package com.servicesimpl;

import com.daoapi.UserDao;
import com.entities.User;
import com.servicesapi.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
