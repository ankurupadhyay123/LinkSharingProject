package com.servicesapi;

import com.entities.Topic;
import com.entities.User;

import java.util.List;

/**
 * Created by ankur on 14/7/17.
 */
public interface TopicService {
    boolean save(User user, String topicName, String visibility);
    List<Topic> getTopicList(String query);
    Topic findByname(String topicname);
}
