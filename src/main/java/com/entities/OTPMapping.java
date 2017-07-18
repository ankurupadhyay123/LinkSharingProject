package com.entities;

import javax.persistence.*;

/**
 * Created by ankur on 14/7/17.
 */

@Entity
public class OTPMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer OTPId;

    @Column
    private int otp;

    @Column
    private String email;

    public Integer getOTPId() {
        return OTPId;
    }

    public void setOTPId(Integer OTPId) {
        this.OTPId = OTPId;
    }

    public int getOtp() { return otp; }

    public void setOtp(int otp) { this.otp = otp; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "OTPMapping [OTPId=" + OTPId + ", otp=" + otp + ", email=" + email + "]";
    }
}
