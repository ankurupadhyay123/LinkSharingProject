package com.controllers;

import com.entities.OTPMapping;
import com.servicesapi.EmailService;
import com.servicesapi.OTPValidation;
import com.servicesapi.UpdationService;
import com.servicesapi.ValidationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ankur on 14/7/17.
 */
@Controller
public class ValidationController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPValidation otpValidation;

    @Autowired
    private UpdationService updationService;

    @Autowired
    private ValidationService validationService;

    private ModelAndView view;
    private OTPMapping otpMapping;

    @RequestMapping(value = "/sendOTP", method = RequestMethod.POST)
    public @ResponseBody ModelAndView sendOTP(@RequestParam("email_name") String email, HttpServletRequest request){

        if(emailService.sendOTPToUser(email)){
            view = new ModelAndView();
            view.setViewName("fogetPassword");
        }
        return view;
    }
    @RequestMapping(value = "/forgotpasswordform", method = RequestMethod.POST)
    public @ResponseBody ModelAndView updatePassword(@RequestParam("email") String email, @RequestParam("otp") String otp,@RequestParam("password") String password,HttpServletRequest request){

        System.out.println("inside update password");
        otpMapping = otpValidation.findOTPByEmail(email);
        int OTP = Integer.parseInt(otp);
        System.out.println("otp code "+otpMapping);
        if(OTP == otpMapping.getOtp()){
            if(updationService.updatePassword(email,password)){
                view = new ModelAndView();
                view.setViewName("profile");
            }
        }
        return view;
    }

    @RequestMapping(value = "/checkunique", method = RequestMethod.POST)
    public void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("val");
        Object result = validationService.checkUniqueUsername(userName);
        if(result != null)
        {
            response.getWriter().write("<font color=red>Username already taken</font>");

        }else {
            response.getWriter().write("<font color=green>Username available</font>");
        }
    }

    @RequestMapping(value = "/checkuniqueemail", method = RequestMethod.POST)
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("val");
        Object result = validationService.checkUniqueEmail(email);
        if(result != null)
        {
            response.getWriter().write("<font color=red>EmailId already taken</font>");

        }else {
            response.getWriter().write("<font color=green>EmailID available</font>");
        }
    }
}
