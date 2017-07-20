package com.servicesapi;

import com.entities.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ankur on 14/7/17.
 */
public interface UpdationService {
    boolean updatePassword(String email,String password);
    boolean updateProfile(User user, MultipartFile newPhoto, String email);
}
