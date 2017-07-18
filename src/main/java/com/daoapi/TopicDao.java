package com.daoapi;

import com.entities.Topic;
import com.entities.User;

/**
 * Created by ankur on 14/7/17.
 */
public interface TopicDao {
    boolean save(User user, String topicName, String visibility);
}
