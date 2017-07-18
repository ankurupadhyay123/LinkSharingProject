package com.servicesapi;

import com.entities.User;

import java.sql.SQLException;

/**
 * Created by ankur on 13/7/17.
 */
public interface LoginService {
    User getUserByUsernameAndPassword(String username, String password) throws SQLException;
}
