package com.servicesimpl;

import com.daoapi.UserDao;
import com.servicesapi.UpdationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ankur on 14/7/17.
 */

@Service
public class UpdationServiceImpl implements UpdationService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean updatePassword(String email, String password) { return userDao.updatePassword(email,password); }
}
