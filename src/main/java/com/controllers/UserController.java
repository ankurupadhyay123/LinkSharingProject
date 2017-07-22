package com.controllers;

import com.constants.LinksharingConstants;
import com.entities.User;
import com.servicesapi.ResourceService;
import com.servicesapi.UpdationService;
import com.util.GetSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    ModelAndView view;

    @Autowired
    private UpdationService updationService;

    @Autowired
    private ResourceService resourceService;

    private HttpSession session;

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public ModelAndView getUserProfile() {
        view = new ModelAndView("userProfile");
        return view;
    }

    @RequestMapping(value = "/updateUserProfile", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView updateProfileData(@ModelAttribute User user, @RequestParam("foto") MultipartFile fileUpload, HttpServletRequest request) throws IOException {
        System.out.println("fileupload " + fileUpload);
        System.out.println("inside method");
        User user1 = (User) GetSession.getSession(request).getAttribute(LinksharingConstants.USER_DETAILS);
            if (updationService.updateProfile(user, fileUpload,user1.getEmail())) {
                view = new ModelAndView("userProfile");
            }
        return view;
    }

    @RequestMapping(value = "/updatePasswordForm", method = RequestMethod.POST)
    public @ResponseBody ModelAndView updatePassword(@RequestParam("password") String password,HttpServletRequest request){
        User user = (User)GetSession.getSession(request).getAttribute(LinksharingConstants.USER_DETAILS);
            if(updationService.updatePassword(user.getEmail(),password)){
                view = new ModelAndView();
                view.setViewName("userProfile");
            }
        return view;
    }

    @RequestMapping(value = "/logOut")
    public ModelAndView logOut(HttpServletRequest request){
        session = GetSession.getSession(request);
        session.invalidate();
        view = new ModelAndView();
        Map<String,Object> userModel = new HashMap<>();
        String errorMessage = "You are successfully logged out";
        userModel.put("recentResourceList",resourceService.getRecentResources());
        userModel.put("logoutMessage",errorMessage);
        view = new ModelAndView("welcome",userModel);
        return view;
    }
}
