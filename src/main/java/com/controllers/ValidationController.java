package com.controllers;

import com.entities.OTPMapping;
import com.servicesapi.EmailService;
import com.servicesapi.OTPValidation;
import com.servicesapi.UpdationService;
import com.servicesapi.ValidationService;
import com.util.GetSession;
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
    HttpSession session;

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
                view.setViewName("dashBoard");
            }
        }
        return view;
    }

    @RequestMapping(value = "/checkUniqueUserName", method = RequestMethod.POST)
    public @ResponseBody String checkUsername(@RequestParam("name") String userName) throws IOException {
        if(userName == "" || userName == null){
            return null;
        }else {
            Object result = validationService.checkUniqueUsername(userName);
            if(result != null)
            {
                return "Username already taken";
            }else {
                return "Username available";
            }
        }
    }

    @RequestMapping(value = "/checkUniqueEmail", method = RequestMethod.POST)
    public String checkEmail(@RequestParam("mail") String email) throws IOException {
        if(email == "" || email == null){
            return null;
        }else {
            Object result = validationService.checkUniqueEmail(email);
            if(result != null)
            {
                return "Email already taken";

            }else {
                return "EmailID available";
            }
        }
    }

    @RequestMapping(value = "/checkUniqueTopic", method = RequestMethod.POST)
    public String checkTopic(@RequestParam("topic") String topic) throws IOException {
        if(topic == "" || topic == null){
            return null;
        }else {
            Object result = validationService.findTopicByname(topic);
            if(result != null)
            {
                return "Topic with same name exist";

            }else {
                return "Topic available";
            }
        }
    }
}
