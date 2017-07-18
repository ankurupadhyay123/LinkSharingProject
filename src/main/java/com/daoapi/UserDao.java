package com.daoapi;

import com.entities.User;

import java.sql.SQLException;

public interface UserDao {

	boolean save(User user);

	User findByID(Integer id);

	User findByEmail(String email);

	User findByUsernameAndPass(String userName, String password) throws SQLException;

	boolean updatePassword(String email,String password);

	byte[] getProfilePhoto(User user);

}
