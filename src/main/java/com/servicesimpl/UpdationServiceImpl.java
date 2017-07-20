package com.servicesimpl;

import com.daoapi.UserDao;
import com.entities.User;
import com.servicesapi.UpdationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ankur on 14/7/17.
 */

@Service
public class UpdationServiceImpl implements UpdationService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean updatePassword(String email, String password) { return userDao.updatePassword(email,password); }

    @Override
    public boolean updateProfile(User user, MultipartFile newPhoto, String email) { return userDao.updateUserData(user,newPhoto,email); }

}
