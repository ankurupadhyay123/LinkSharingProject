package com.servicesapi;

/**
 * Created by ankur on 18/7/17.
 */
public interface ValidationService {
    Object checkUniqueEmail(String email);
    Object checkUniqueUsername(String username);
}
