package com.servicesapi;

import com.entities.User;

/**
 * Created by ankur on 14/7/17.
 */
public interface CreateTopicService {
    boolean save(User user, String topicName, String visibility);
}
