package com.controllers;

import com.constants.LinksharingConstants;
import com.entities.Topic;
import com.entities.User;
import com.servicesapi.*;
import com.util.GetSession;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by ankur on 14/7/17.
 */

@Controller
public class DataController {

    @Autowired
    TopicService topicService;

    @Autowired
    PhotoService photoService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    ResourceService resourceService;

    HttpSession session;
    ModelAndView view;

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadResource( HttpServletRequest request,
                                  HttpServletResponse response)
    {
        try
        {
            String fileName=request.getParameter("filePath");

            fileName=fileName.replaceAll("'","\\");
            fileName=fileName.substring(2,fileName.length()-2);
            System.out.println(fileName);

            fileName="file:///"+fileName;
            System.out.println(fileName);

            URL url = new URL(fileName);
            final URLConnection connection = url.openConnection();

            final InputStream is = connection.getInputStream();
            OutputStream outStream = response.getOutputStream();

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileName.substring(79));
            response.setHeader(headerKey,headerValue);

            int data;
            byte b[]=new byte[999999];
            while ((data = is.read(b)) != -1) {
                byte tmp[]= ArrayUtils.subarray(b, 0, data);
                outStream.write(tmp);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/createTitle", method = RequestMethod.POST)
    public @ResponseBody
    boolean getSaved(@RequestParam("topicName") String topicName, @RequestParam("visibility") String visibility, HttpServletRequest request) {

        session = GetSession.getSession(request);
        User user = (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        if(topicService.save(user, topicName, visibility)){
            return true;
        }else {
            return false;
        }

    }

    @RequestMapping(value = "/createLinkResource", method = RequestMethod.POST)
    public @ResponseBody
    boolean createLinkResource(@RequestParam("linkUrl") String linkUrl, @RequestParam("description") String description, @RequestParam("topic") String topicname, HttpServletRequest request) {

        session = GetSession.getSession(request);
        User user = (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        Topic topic = topicService.findByname(topicname);
        if(resourceService.saveLinkUrlResource(user,linkUrl,description,topic)){
            return true;
        }else {
            return false;
        }
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

    @RequestMapping(value = "/sendInvitation",method = RequestMethod.POST)
    public @ResponseBody
    void sendInvitation(@RequestParam("email") String email, @RequestParam("topicSearchTag") String topic){

    }

    @RequestMapping(value = "/getphoto")
    public @ResponseBody void getPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = GetSession.getSession(request);
        User user =  (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        byte[] brr =  photoService.getImage(user);
        response.getOutputStream().write(brr);
    }

    @RequestMapping(value = "/getsubscriptions")
    public @ResponseBody void getsubscriptions(HttpServletRequest request) throws IOException {
        session = GetSession.getSession(request);
        User user =  (User) session.getAttribute(LinksharingConstants.USER_DETAILS);
        Object result = subscriptionService.getSubcriptionsOfUser(user);
        System.out.println("result is "+result);
    }

    @RequestMapping(value="/getAllTags", method = RequestMethod.POST)
    public @ResponseBody List<Topic> getAllTopics(@RequestParam("term") String query){
        System.out.println("on controller"+query);
        List<Topic> tags = topicService.getTopicAllList(query);
        return tags;
    }

    @RequestMapping(value="/getAllPublicTags", method = RequestMethod.POST)
    public @ResponseBody List<Topic> getPublicTopics(@RequestParam("term") String query){
        System.out.println("on controller"+query);
        List<Topic> tags = topicService.getPublicTopicList(query);
        return tags;
    }

    @RequestMapping("/imageFetch")
    public void imageFetcher(@RequestParam("email") String email, HttpServletResponse response)
    {
        User user = userDetailService.getUserByEmail(email);
        System.out.println("user is "+user);
        byte[] photo = user.getPhoto();

        try {
            response.getOutputStream().write(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}