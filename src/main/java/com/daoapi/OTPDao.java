package com.daoapi;

import com.entities.OTPMapping;

/**
 * Created by ankur on 14/7/17.
 */
public interface OTPDao {

    boolean save(OTPMapping otp);
    OTPMapping findByEmail(String email);
}
