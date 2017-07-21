package com.servicesapi;

import com.entities.User;

public interface UserDetailService {

    User getUserByEmail(String email);
}
