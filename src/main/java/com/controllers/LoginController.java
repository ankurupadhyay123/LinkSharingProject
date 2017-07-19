package com.controllers;

/**
 * Created by ankur on 12/7/17.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.constants.LinksharingConstants;
import com.entities.User;
import com.servicesapi.LoginService;
import com.servicesapi.RegisterService;
import com.servicesapi.SubscriptionService;
import com.util.GetSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SubscriptionService subscriptionService;

    private ModelAndView view;
    private User user;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        view = new ModelAndView("welcome");
        return view;
    }

    HttpSession session;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getSaved(@ModelAttribute User user, @RequestParam("pho") MultipartFile fileUpload, HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView();
        System.out.println("inside register");
        System.out.println("fileupload "+fileUpload);
        if (fileUpload != null && fileUpload.getSize() > 0) {
                System.out.println("Saving file: " + fileUpload.getOriginalFilename());
                user.setPhoto(fileUpload.getBytes());
            System.out.println("length "+ fileUpload.getSize());
        }
        else {
            System.out.println("inside register else");
            File file=new File("/home/ankur/IdeaProjects/LinkSharing/src/main/webapp/WEB-INF/views/assets/images.png");
            FileInputStream fis=new FileInputStream(file);
            byte b[]=new byte[(int)file.length()];
            fis.read(b);
            user.setPhoto(b);
        }
        if(registerService.save(user)){
            session = GetSession.getSession(request);
            session.setAttribute(LinksharingConstants.USER_DETAILS,user);
            view.setViewName("dashBoard");
            return view;
        }
        else {
            view.setViewName("welcome");
            return view;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ModelAndView login(@RequestParam("uname") String name, @RequestParam("pass") String password, HttpServletRequest request){
        session = GetSession.getSession(request);
        try {
            user = loginService.getUserByUsernameAndPassword(name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){
            session.setAttribute(LinksharingConstants.USER_DETAILS,user);
            Map<String,Object> userModel = new HashMap<>();
            userModel.put("subscriptionCount",subscriptionService.getSubcriptionsOfUser(user));
            view = new ModelAndView("dashBoard",userModel);
            return view;
        }else {
            Map<String,Boolean> userModel = new HashMap<>();
            userModel.put("usernotvalid",true);
            view = new ModelAndView("welcome",userModel);
            return view;
        }
    }

    @RequestMapping(value = "/forgotpassword")
    public @ResponseBody ModelAndView forgotPassword(){
            view = new ModelAndView();
            view.setViewName("fogetPassword");
            return view;
    }
}