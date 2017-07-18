package com.controllers;

/**
 * Created by ankur on 12/7/17.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entities.User;
import com.servicesapi.EmailService;
import com.servicesapi.LoginService;
import com.servicesapi.RegisterService;
import com.servicesapi.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/")
public class MainController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private MessageSource messageSource;

    private ModelAndView view;
    private User user;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        view = new ModelAndView("welcome");
        return view;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getSaved(@ModelAttribute User user, @RequestParam("phot") MultipartFile[] fileUpload, HttpServletRequest request, BindingResult bindingResult) throws IOException {
        //Map<String, Object> map = new HashMap<String, Object>();
        ModelAndView view = new ModelAndView();
        System.out.println("fileupload "+fileUpload);
        if (fileUpload != null && fileUpload.length > 0) {
            for (MultipartFile aFile : fileUpload){
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                user.setPhoto(aFile.getBytes());
            }
            System.out.println("length "+ fileUpload.length);
        }
        else {
            File file=new File("/home/ankur/IdeaProjects/LinkSharing/src/main/webapp/WEB-INF/views/assets/images.png");
            FileInputStream fis=new FileInputStream(file);
            byte b[]=new byte[(int)file.length()];
            fis.read(b);
            user.setPhoto(b);
            System.out.println("aya to hai isme");
        }
        if(registerService.save(user)){
            view.setViewName("welcome");
        }
        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ModelAndView login(@RequestParam("uname") String name, @RequestParam("pass") String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        try {
            user = loginService.getUserByUsernameAndPassword(name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){

            //TODO: put userDetails in constants file
            session.setAttribute("UserDetails",user);
            //System.out.println("request is"+request.getAttribute("Body"));
            Map<String,Object> userModel = new HashMap<>();
            userModel.put("subscriptionCount",subscriptionService.getSubcriptionsOfUser(user));
            view = new ModelAndView("profile",userModel);
        }
        return view;
    }

    @RequestMapping(value = "/forgotpassword")
    public @ResponseBody ModelAndView forgotPassword(){

            view = new ModelAndView();
            view.setViewName("fogetPassword");
            return view;
    }
}
