package com.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.constants.LinksharingConstants;
import com.entities.User;
import com.servicesapi.*;
import com.util.GetSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private ResourceService resourceService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private TopicService topicService;

    private ModelAndView view;
    private User user;
    private HttpSession session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage(HttpServletRequest request) {
        session = GetSession.getSession(request);
        User user = (User)session.getAttribute(LinksharingConstants.USER_DETAILS);
        if(user != null){
            view = new ModelAndView("dashBoard");
        }else {
            Map<String,Object> userModel = new HashMap<>();
            userModel.put("recentResourceList",resourceService.getRecentResources());
            view = new ModelAndView("welcome",userModel);
        }
        return view;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ModelAndView getSaved(@ModelAttribute User user, @RequestParam("pho") MultipartFile fileUpload, HttpServletRequest request) throws IOException {
        ModelAndView view = new ModelAndView();
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
            Map<String,Object> userModel = new HashMap<>();
            userModel.put("recentResourceList",resourceService.getRecentResources());
            view = new ModelAndView("welcome",userModel);
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
            userModel.put("topicCount",topicService.getNoOfTopics(user));
            userModel.put("subscribedTopicsList",subscriptionService.getSubscribedTopics(user));
            userModel.put("subscriptionsForEachTopic",subscriptionService.getSubscriptionsForEachTopic());
            userModel.put("inboxResourceList",resourceService.getInboxResource(user));
            view = new ModelAndView("dashBoard",userModel);
            return view;
        }else {
            Map<String,Object> userModel = new HashMap<>();
            String errorMessage = "Invalid Username or Password";
            userModel.put("usernotvalid",true);
            userModel.put("recentResourceList",resourceService.getRecentResources());
            userModel.put("message",errorMessage);
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
