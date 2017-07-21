package com.servicesapi;

import com.entities.Topic;
import com.entities.User;

import java.util.List;

public interface TopicService {
    boolean save(User user, String topicName, String visibility);
    List<Topic> getTopicAllList(String query);
    Topic findByname(String topicname);
    Long getNoOfTopics(User user);
    List<Topic> getPublicTopicList(String query);
}
