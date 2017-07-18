package com.servicesimpl;

import com.daoapi.OTPDao;
import com.entities.OTPMapping;
import com.servicesapi.OTPValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ankur on 14/7/17.
 */
@Service
public class OTPValidationImpl implements OTPValidation {

    @Autowired
    private OTPDao otpDao;

    @Override
    public OTPMapping findOTPByEmail(String email) { return otpDao.findByEmail(email); }
}
