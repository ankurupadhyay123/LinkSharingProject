package com.servicesimpl;

import com.daoapi.TopicDao;
import com.entities.Topic;
import com.entities.User;
import com.servicesapi.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ankur on 14/7/17.
 */

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public boolean save(User user, String topicName, String visibility)
    { return topicDao.save(user,topicName, visibility); }

    @Override
    public List<Topic> getTopicList(String query)
    { return topicDao.getAllPublicTopics(query); }

    @Override
    public Topic findByname(String topicname)
    { return topicDao.findTopicByname(topicname); }

    @Override
    public Long getNoOfTopics(User user){
        return topicDao.getNoOfTopics(user);
    }
}
