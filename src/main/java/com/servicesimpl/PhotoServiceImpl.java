package com.servicesimpl;

import com.daoapi.UserDao;
import com.entities.User;
import com.servicesapi.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private UserDao userDao;

    @Override
    public byte[] getImage(User user) { return userDao.getProfilePhoto(user); }
}
