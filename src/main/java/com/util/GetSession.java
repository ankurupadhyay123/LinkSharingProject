package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ankur on 18/7/17.
 */
public class GetSession {

    private static HttpSession session;

    public static HttpSession getSession(HttpServletRequest request){
        return session = request.getSession() ;
    }
}
