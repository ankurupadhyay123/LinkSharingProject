package com.servicesimpl;

import com.daoapi.UserDao;
import com.entities.User;
import com.servicesapi.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by ankur on 13/7/17.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsernameAndPassword(String username, String password)
            throws SQLException
    {return userDao.findByUsernameAndPass(username,password); }
}
