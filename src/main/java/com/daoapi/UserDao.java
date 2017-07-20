package com.daoapi;

import com.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

public interface UserDao {

	boolean save(User user);

	User findByEmail(String email);

	User findByUsernameAndPass(String userName, String password) throws SQLException;

	boolean updatePassword(String email,String password);

	byte[] getProfilePhoto(User user);

	boolean updateUserData(User user, MultipartFile newPhoto, String email);

}
