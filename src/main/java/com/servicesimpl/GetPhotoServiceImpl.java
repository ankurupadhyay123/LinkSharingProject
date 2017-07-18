package com.servicesimpl;

import com.daoapi.UserDao;
import com.entities.User;
import com.servicesapi.GetPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ankur on 17/7/17.
 */

@Service
public class GetPhotoServiceImpl implements GetPhotoService {

    @Autowired
    UserDao userDao;

    @Override
    public byte[] getImage(User user) { return userDao.getProfilePhoto(user); }
}
