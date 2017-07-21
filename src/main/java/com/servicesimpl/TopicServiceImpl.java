package com.servicesimpl;

import com.daoapi.TopicDao;
import com.entities.Topic;
import com.entities.User;
import com.servicesapi.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public boolean save(User user, String topicName, String visibility)
    { return topicDao.save(user,topicName, visibility); }

    @Override
    public List<Topic> getTopicAllList(String query)
    { return topicDao.getAllTopics(query); }

    @Override
    public Topic findByname(String topicname)
    { return topicDao.findTopicByname(topicname); }

    @Override
    public Long getNoOfTopics(User user){
        return topicDao.getNoOfTopics(user);
    }

    @Override
    public List<Topic> getPublicTopicList(String query)
    { return topicDao.getAllPublicTopics(query); }
}
