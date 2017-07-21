package com.servicesapi;

import com.entities.User;

public interface PhotoService {

    byte[] getImage(User user);
}
