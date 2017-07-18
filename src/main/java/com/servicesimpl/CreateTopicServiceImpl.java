package com.servicesimpl;

import com.daoapi.TopicDao;
import com.entities.User;
import com.servicesapi.CreateTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ankur on 14/7/17.
 */

@Service
public class CreateTopicServiceImpl implements CreateTopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public boolean save(User user, String topicName, String visibility)
    { return topicDao.save(user,topicName, visibility); }
}
