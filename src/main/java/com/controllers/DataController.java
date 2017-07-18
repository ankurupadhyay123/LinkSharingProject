package com.controllers;

import com.constants.LinksharingConstants;
import com.entities.User;
import com.servicesapi.CreateTopicService;
import com.servicesapi.GetPhotoService;
import com.servicesapi.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ankur on 14/7/17.
 */

@Controller
public class DataController {

    @Autowired
    CreateTopicService createTopicService;

    @Autowired
    GetPhotoService getPhotoService;

    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping(value = "/createTitle", method = RequestMethod.POST)
    public @ResponseBody
    Boolean getSaved(@RequestParam("topicName") String topicName, @RequestParam("visibility") String visibility, HttpServletRequest request) {
        //Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserDetails");
        return createTopicService.save(user, topicName, visibility);

    }

    @RequestMapping(value = "/getphoto")
    public @ResponseBody void getPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user =  (User) session.getAttribute("UserDetails");
        byte[] brr =  getPhotoService.getImage(user);
        response.getOutputStream().write(brr);
    }

    @RequestMapping(value = "/getsubscriptions")
    public @ResponseBody void getsubscriptions(HttpServletRequest request) throws IOException {
        System.out.println("Inside get subsrcriptions");
        HttpSession session = request.getSession();
        User user =  (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        Object result = subscriptionService.getSubcriptionsOfUser(user);
        System.out.println("result is "+result);
    }
}
