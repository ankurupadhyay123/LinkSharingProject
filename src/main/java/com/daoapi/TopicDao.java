package com.daoapi;

import com.entities.Topic;
import com.entities.User;

import java.util.List;

public interface TopicDao {
    boolean save(User user, String topicName, String visibility);
    List<Topic> getAllTopics(String query);
    List<Topic> getAllPublicTopics(String query);
    Topic findTopicByname(String topicname);
    Long getNoOfTopics(User user);
}
