package com.controllers;

import com.constants.LinksharingConstants;
import com.entities.Topic;
import com.entities.User;
import com.servicesapi.ResourceService;
import com.servicesapi.TopicService;
import com.servicesapi.GetPhotoService;
import com.servicesapi.SubscriptionService;
import com.util.GetSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import java.io.*;
import java.util.List;

/**
 * Created by ankur on 14/7/17.
 */

@Controller
public class DataController {

    @Autowired
    TopicService topicService;

    @Autowired
    GetPhotoService getPhotoService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    ResourceService resourceService;

    HttpSession session;

    @RequestMapping(value = "/createTitle", method = RequestMethod.POST)
    public @ResponseBody
    void getSaved(@RequestParam("topicName") String topicName, @RequestParam("visibility") String visibility, HttpServletRequest request) {

        session = GetSession.getSession(request);
        User user = (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        topicService.save(user, topicName, visibility);
    }

    @RequestMapping(value = "/createLinkResource", method = RequestMethod.POST)
    public @ResponseBody
    void createLinkResource(@RequestParam("linkUrl") String linkUrl, @RequestParam("description") String description, @RequestParam("topic") String topicname, HttpServletRequest request) {

        session = GetSession.getSession(request);
        User user = (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        Topic topic = topicService.findByname(topicname);
        resourceService.saveLinkUrlResource(user,linkUrl,description,topic);
    }

    @RequestMapping(value = "/createDocumentResource", method = RequestMethod.POST)
    public @ResponseBody
    void createDocResource(@RequestParam("fileUrl") MultipartFile fileUrl, @RequestParam("description") String description, @RequestParam("topic") String topicname, HttpServletRequest request) {
        session = GetSession.getSession(request);
        User user = (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        if (fileUrl != null && fileUrl.getSize()>0) {
            byte[] bytes = new byte[0];
            try {
                bytes = fileUrl.getBytes();
                String path = "/home/ankur/IdeaProjects/LinkSharing/src/main/webapp/WEB-INF/views/assets/"
                        + user.getUserName() + fileUrl.getOriginalFilename();
                File serverFile = new File(path);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                Topic topic = topicService.findByname(topicname);
                resourceService.saveDocUrl(user, path, description, topic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/getphoto")
    public @ResponseBody void getPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = GetSession.getSession(request);
        User user =  (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        byte[] brr =  getPhotoService.getImage(user);
        response.getOutputStream().write(brr);
    }

    @RequestMapping(value = "/getsubscriptions")
    public @ResponseBody void getsubscriptions(HttpServletRequest request) throws IOException {
        session = GetSession.getSession(request);
        User user =  (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        Object result = subscriptionService.getSubcriptionsOfUser(user);
        System.out.println("result is "+result);
    }

    @RequestMapping(value="/getTags", method = RequestMethod.POST)
    public @ResponseBody List<Topic> getPublicTopics(@RequestParam("term") String query){
        System.out.println("on controller"+query);
        List<Topic> tags = topicService.getTopicList(query);
        return tags;
    }
}