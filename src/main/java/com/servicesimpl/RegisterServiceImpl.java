package com.servicesimpl;

import com.daoapi.UserDao;
import com.entities.User;
import com.servicesapi.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ankur on 12/7/17.
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean save(User user) {return userDao.save(user);}
}
