package com.servicesapi;

import com.entities.OTPMapping;

/**
 * Created by ankur on 14/7/17.
 */
public interface OTPValidation {
    OTPMapping findOTPByEmail(String email);
}
