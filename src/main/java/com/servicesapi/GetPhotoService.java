package com.servicesapi;

import com.entities.User;

/**
 * Created by ankur on 17/7/17.
 */
public interface GetPhotoService {

    byte[] getImage(User user);
}
